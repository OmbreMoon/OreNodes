package com.ombremoon.orenodes.common.world;

import com.ombremoon.orenodes.Constants;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;
import org.slf4j.Logger;

import java.util.Map;

public class GeneratorData extends SavedData {
    private static final Logger LOGGER = Constants.LOG;
    private final Map<BlockPos, GeneratorValue> cachedGenerators = new Object2ObjectOpenHashMap<>();

    private GeneratorData() {
    }

    public static GeneratorData get(ServerLevelAccessor level) {
        ServerLevel serverLevel = level.getServer().getLevel(Level.OVERWORLD);
        DimensionDataStorage storage = serverLevel.getDataStorage();
        return storage.computeIfAbsent(GeneratorData::load, GeneratorData::create, "ore_generators");
    }

    private static GeneratorData create() {
        return new GeneratorData();
    }

    public boolean cacheGenerator(ServerLevel level, BlockPos blockPos, BlockState state) {
        if (this.cachedGenerators.containsKey(blockPos))
            return false;

        this.cachedGenerators.put(blockPos, new GeneratorValue(state, level.getGameTime()));
        this.setDirty();
        return true;
    }

    public void tickGenerators(ServerLevel level) {
        for (var entry : this.cachedGenerators.entrySet()) {
            BlockPos blockPos = entry.getKey();
            BlockState state = entry.getValue().blockState();
            BlockState currentState = level.getBlockState(blockPos);
            long time = entry.getValue().breakTime();
            if (level.getGameTime() >= time + 20) {
                this.cachedGenerators.remove(blockPos);
                if (currentState.isAir()) {
                    level.setBlock(blockPos, state, 3);
                    level.playSound(null, blockPos, SoundEvents.DEEPSLATE_FALL, SoundSource.BLOCKS, 13, 3);
                }

                this.setDirty();
            }
        }
    }

    @Override
    public CompoundTag save(CompoundTag pCompoundTag) {
        ListTag generators = new ListTag();
        for (var entry : this.cachedGenerators.entrySet()) {
            CompoundTag tag = new CompoundTag();
            tag.put("GeneratorPos", NbtUtils.writeBlockPos(entry.getKey()));
            GeneratorValue.CODEC
                    .encodeStart(NbtOps.INSTANCE, entry.getValue())
                    .resultOrPartial(LOGGER::error)
                    .ifPresent(nbt -> tag.put("GeneratorValue", nbt));
            generators.add(tag);
        }
        pCompoundTag.put("Generators", generators);
        return pCompoundTag;
    }

    public static GeneratorData load(CompoundTag nbt) {
        GeneratorData data = create();
        if (nbt.contains("Generators", 9)) {
            ListTag generators = nbt.getList("Generators", 10);
            for (int i = 0; i < generators.size(); i++) {
                CompoundTag tag = generators.getCompound(i);
                BlockPos blockPos = NbtUtils.readBlockPos(tag);
                GeneratorValue value = GeneratorValue.CODEC
                        .parse(NbtOps.INSTANCE, tag)
                        .resultOrPartial(LOGGER::error)
                        .orElse(null);
                if (value != null) {
                    data.cachedGenerators.put(blockPos, value);
                }
            }
        }
        return data;
    }
}
