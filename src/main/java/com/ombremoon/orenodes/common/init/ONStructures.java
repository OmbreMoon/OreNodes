package com.ombremoon.orenodes.common.init;

import com.mojang.serialization.Codec;
import com.ombremoon.orenodes.Constants;
import com.ombremoon.orenodes.common.world.structure.OreNodePieces;
import com.ombremoon.orenodes.common.world.structure.OreNodeStructure;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ONStructures {
    public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registries.STRUCTURE_TYPE, Constants.MOD_ID);
    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE = DeferredRegister.create(Registries.STRUCTURE_PIECE, Constants.MOD_ID);

    public static final Supplier<StructureType<OreNodeStructure>> ORE_NODE = STRUCTURES.register("ore_node", () -> structureType(OreNodeStructure.CODEC));

    public static final Supplier<StructurePieceType> ORE_NODE_PIECE = structureTemplatePiece("onp", OreNodePieces.OreNodePiece::new);

    private static Supplier<StructurePieceType> structurePiece(String name, StructurePieceType templateType) {
        return STRUCTURE_PIECE.register(name, () -> templateType);
    }

    private static Supplier<StructurePieceType> structureTemplatePiece(String name, StructurePieceType.StructureTemplateType templateType) {
        return structurePiece(name, templateType);
    }

    private static <T extends Structure> StructureType<T> structureType(Codec<T> structureCodec) {
        return () -> structureCodec;
    }

    public static void register(IEventBus modEventBus) {
        STRUCTURES.register(modEventBus);
        STRUCTURE_PIECE.register(modEventBus);
    }
}
