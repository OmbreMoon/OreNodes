package com.ombremoon.orenodes.common.world.structure;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.ombremoon.orenodes.CommonClass;
import com.ombremoon.orenodes.common.init.ONStructures;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.Util;
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

import java.util.Collections;
import java.util.Map;

public class OreNodePieces {
    private static final ResourceLocation NODE_1 = CommonClass.customLocation("gold_ore_node_1");
    private static final ResourceLocation NODE_2 = CommonClass.customLocation("gold_ore_node_2");
    private static final ResourceLocation NODE_3 = CommonClass.customLocation("gold_ore_node_4");
    private static final ResourceLocation NODE_4 = CommonClass.customLocation("hematite_ore_node_1");
    private static final ResourceLocation NODE_5 = CommonClass.customLocation("hematite_ore_node_2");
    private static final ResourceLocation NODE_6 = CommonClass.customLocation("hematite_ore_node_3");
    private static final ResourceLocation NODE_7 = CommonClass.customLocation("malachite_ore_node_1");
    private static final ResourceLocation NODE_8 = CommonClass.customLocation("malachite_ore_node_2");
    private static final ResourceLocation NODE_9 = CommonClass.customLocation("malachite_ore_node_3");
    private static final ResourceLocation NODE_10 = CommonClass.customLocation("sphalerite_ore_node_1");
    private static final ResourceLocation NODE_11 = CommonClass.customLocation("sphalerite_ore_node_2");
    private static final ResourceLocation NODE_12 = CommonClass.customLocation("sphalerite_ore_node_3");
    private static final ResourceLocation NODE_13 = CommonClass.customLocation("wolframite_ore_node_1");
    private static final ResourceLocation NODE_14 = CommonClass.customLocation("wolframite_ore_node_2");
    private static final ResourceLocation NODE_15 = CommonClass.customLocation("wolframite_ore_node_3");
    private static final ResourceLocation[] NODES = new ResourceLocation[]{NODE_1, NODE_2, NODE_3, NODE_4, NODE_5, NODE_6, NODE_7, NODE_8, NODE_9, NODE_10, NODE_11, NODE_12, NODE_13, NODE_14, NODE_15};
    private static final Map<ResourceLocation, Integer> OFFSETS;

    public static void addPieces(StructureTemplateManager manager, BlockPos pos, Rotation rotation, StructurePieceAccessor pieces, RandomSource random) {
        pieces.addPiece(new OreNodePiece(manager, Util.getRandom(NODES, random), pos, rotation));
    }

    static {
        Map<ResourceLocation, Integer> offsets = new Object2IntOpenHashMap<>();
        offsets.put(NODE_1, -3);
        offsets.put(NODE_2, -4);
        offsets.put(NODE_3, -5);
        offsets.put(NODE_4, -3);
        offsets.put(NODE_5, -4);
        offsets.put(NODE_6, -5);
        offsets.put(NODE_7, -3);
        offsets.put(NODE_8, -4);
        offsets.put(NODE_9, -5);
        offsets.put(NODE_10, -3);
        offsets.put(NODE_11, -4);
        offsets.put(NODE_12, -5);
        offsets.put(NODE_13, -3);
        offsets.put(NODE_14, -4);
        offsets.put(NODE_15, -5);
        OFFSETS = Collections.unmodifiableMap(offsets);
    }

    public static class OreNodePiece extends TemplateStructurePiece {

        public OreNodePiece(StructureTemplateManager pStructureTemplateManager, ResourceLocation pLocation, BlockPos pStartPos, Rotation pRotation) {
            super(ONStructures.ORE_NODE_PIECE.get(), 0, pStructureTemplateManager, pLocation, pLocation.getPath(), makeSettings(pRotation), pStartPos);
        }

        public OreNodePiece(StructureTemplateManager pStructureTemplateManager, CompoundTag pTag) {
            super(ONStructures.ORE_NODE_PIECE.get(), pTag, pStructureTemplateManager, (p_227589_) -> makeSettings(Rotation.valueOf(pTag.getString("Rot"))));
        }

        private static StructurePlaceSettings makeSettings(Rotation pRotation) {
            return (new StructurePlaceSettings()).setRotation(pRotation).setMirror(Mirror.NONE).addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
        }

        protected void addAdditionalSaveData(StructurePieceSerializationContext pContext, CompoundTag pTag) {
            super.addAdditionalSaveData(pContext, pTag);
            pTag.putString("Rot", this.placeSettings.getRotation().name());
        }

        @Override
        protected void handleDataMarker(String pName, BlockPos pPos, ServerLevelAccessor pLevel, RandomSource pRandom, BoundingBox pBox) {

        }

        @Override
        public void postProcess(WorldGenLevel pLevel, StructureManager pStructureManager, ChunkGenerator pGenerator, RandomSource pRandom, BoundingBox pBox, ChunkPos pChunkPos, BlockPos pPos) {
            ResourceLocation resourcelocation = CommonClass.customLocation(this.templateName);
            int yOffset = OFFSETS.get(resourcelocation);
            BlockPos blockPos = this.templatePosition;
            int i = pLevel.getHeight(Heightmap.Types.WORLD_SURFACE_WG, blockPos.getX(), blockPos.getZ());
            this.templatePosition = this.templatePosition.offset(0, i - 90 - 1 + yOffset, 0);
            super.postProcess(pLevel, pStructureManager, pGenerator, pRandom, pBox, pChunkPos, pPos);
            this.templatePosition = blockPos;
        }
    }
}
