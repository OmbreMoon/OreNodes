package com.ombremoon.orenodes.common.world.structure;

import com.google.common.collect.ImmutableMap;
import com.ombremoon.orenodes.CommonClass;
import com.ombremoon.orenodes.Constants;
import com.ombremoon.orenodes.common.init.ONStructures;
import com.ombremoon.orenodes.common.world.NodeType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.Map;

public class OreNodePieces {
    private static final String NODE_1 = "_ore_node_1";
    private static final String NODE_2 = "_ore_node_2";
    private static final String NODE_3 = "_ore_node_3";
    private static final Map<String, Integer> OFFSETS = ImmutableMap.of(NODE_1, -3, NODE_2, -4, NODE_3, -4);
    private static final Map<String, Integer> REDSTONE_OFFSETS = ImmutableMap.of(NODE_1, -1, NODE_2, -3, NODE_3, -4);
    private static final Map<String, Integer> ANDESITE_OFFSETS = ImmutableMap.of(NODE_1, -2, NODE_2, -3);

    public static void addPieces(StructureTemplateManager manager, BlockPos pos, Rotation rotation, NodeType node, StructurePieceAccessor pieces, RandomSource random) {
        if (node != NodeType.ANDESITE) {
            if (random.nextDouble() < 0.2) {
                pieces.addPiece(new OreNodePiece(manager, CommonClass.customLocation(node.getSerializedName() + NODE_3), pos, rotation, NODE_3));
            } else if (random.nextDouble() <= 0.55) {
                pieces.addPiece(new OreNodePiece(manager, CommonClass.customLocation(node.getSerializedName() + NODE_2), pos, rotation, NODE_2));
            } else {
                pieces.addPiece(new OreNodePiece(manager, CommonClass.customLocation(node.getSerializedName() + NODE_1), pos, rotation, NODE_1));
            }
        } else {
            if (random.nextDouble() <= 0.55) {
                pieces.addPiece(new OreNodePiece(manager, CommonClass.customLocation(node.getSerializedName() + NODE_1), pos, rotation, NODE_1));
            } else {
                pieces.addPiece(new OreNodePiece(manager, CommonClass.customLocation(node.getSerializedName() + NODE_2), pos, rotation, NODE_2));
            }
        }
    }

    public static class OreNodePiece extends TemplateStructurePiece {
        private final String nodeSize;

        public OreNodePiece(StructureTemplateManager pStructureTemplateManager, ResourceLocation pLocation, BlockPos pStartPos, Rotation pRotation, String nodeSize) {
            super(ONStructures.ORE_NODE_PIECE.get(), 0, pStructureTemplateManager, pLocation, pLocation.getPath(), makeSettings(pRotation), pStartPos);
            this.nodeSize = nodeSize;
        }

        public OreNodePiece(StructureTemplateManager pStructureTemplateManager, CompoundTag pTag) {
            super(ONStructures.ORE_NODE_PIECE.get(), pTag, pStructureTemplateManager, (p_227589_) -> makeSettings(Rotation.valueOf(pTag.getString("Rot"))));
            this.nodeSize = pTag.getString("Node");
        }

        private static StructurePlaceSettings makeSettings(Rotation pRotation) {
            return (new StructurePlaceSettings()).setRotation(pRotation).setMirror(Mirror.NONE).addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
        }

        protected void addAdditionalSaveData(StructurePieceSerializationContext pContext, CompoundTag pTag) {
            super.addAdditionalSaveData(pContext, pTag);
            pTag.putString("Rot", this.placeSettings.getRotation().name());
            pTag.putString("Node", this.nodeSize);
        }

        @Override
        protected void handleDataMarker(String pName, BlockPos pPos, ServerLevelAccessor pLevel, RandomSource pRandom, BoundingBox pBox) {

        }

        @Override
        public void postProcess(WorldGenLevel pLevel, StructureManager pStructureManager, ChunkGenerator pGenerator, RandomSource pRandom, BoundingBox pBox, ChunkPos pChunkPos, BlockPos pPos) {
            var offsets = this.templateName.startsWith("redstone") ? REDSTONE_OFFSETS : this.templateName.startsWith("andesite") ? ANDESITE_OFFSETS : OFFSETS;
            int yOffset = offsets.get(this.nodeSize);
            BlockPos blockPos = this.templatePosition;
            int i = pLevel.getHeight(Heightmap.Types.WORLD_SURFACE_WG, blockPos.getX(), blockPos.getZ());
            this.templatePosition = this.templatePosition.offset(0, i - 90 - 1 + yOffset, 0);
            super.postProcess(pLevel, pStructureManager, pGenerator, pRandom, pBox, pChunkPos, pPos);
            this.templatePosition = blockPos;
        }
    }
}
