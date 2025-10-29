package com.ombremoon.orenodes.common.world.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ombremoon.orenodes.Constants;
import com.ombremoon.orenodes.common.init.ONStructures;
import com.ombremoon.orenodes.common.world.NodeType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;

import java.util.Optional;

public class OreNodeStructure extends Structure {
    public static final Codec<OreNodeStructure> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    settingsCodec(instance),
                    NodeType.CODEC.fieldOf("node").forGetter(oreNodeStructure -> oreNodeStructure.node)
            ).apply(instance, OreNodeStructure::new)
    );
    private final NodeType node;

    public OreNodeStructure(StructureSettings pSettings, NodeType node) {
        super(pSettings);
        this.node = node;
    }

    @Override
    protected Optional<GenerationStub> findGenerationPoint(GenerationContext pContext) {
        return onTopOfChunkCenter(pContext, Heightmap.Types.WORLD_SURFACE_WG, builder -> this.generatePieces(builder, pContext));
    }

    private void generatePieces(StructurePiecesBuilder builder, Structure.GenerationContext context) {
        ChunkPos chunkpos = context.chunkPos();
        WorldgenRandom worldgenrandom = context.random();
        BlockPos blockpos = new BlockPos(chunkpos.getMinBlockX(), 90, chunkpos.getMinBlockZ());
        Rotation rotation = Rotation.getRandom(worldgenrandom);
        OreNodePieces.addPieces(context.structureTemplateManager(), blockpos, rotation, this.node, builder, worldgenrandom);
    }

    @Override
    public StructureType<?> type() {
        return ONStructures.ORE_NODE.get();
    }
}
