package com.ombremoon.orenodes.common.world.structure;

import com.mojang.serialization.Codec;
import com.ombremoon.orenodes.common.init.ONStructures;
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
    public static final Codec<OreNodeStructure> CODEC = simpleCodec(OreNodeStructure::new);

    public OreNodeStructure(StructureSettings pSettings) {
        super(pSettings);
    }

    @Override
    protected Optional<GenerationStub> findGenerationPoint(GenerationContext pContext) {
        return onTopOfChunkCenter(pContext, Heightmap.Types.WORLD_SURFACE_WG, builder -> generatePieces(builder, pContext));
    }

    private static void generatePieces(StructurePiecesBuilder builder, Structure.GenerationContext context) {
        ChunkPos chunkpos = context.chunkPos();
        WorldgenRandom worldgenrandom = context.random();
        BlockPos blockpos = new BlockPos(chunkpos.getMinBlockX(), 90, chunkpos.getMinBlockZ());
        Rotation rotation = Rotation.getRandom(worldgenrandom);
        OreNodePieces.addPieces(context.structureTemplateManager(), blockpos, rotation, builder, worldgenrandom);
    }

    @Override
    public StructureType<?> type() {
        return ONStructures.ORE_NODE.get();
    }
}
