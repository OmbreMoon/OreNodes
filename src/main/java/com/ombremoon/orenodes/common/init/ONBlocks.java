package com.ombremoon.orenodes.common.init;

import com.ombremoon.orenodes.Constants;
import com.ombremoon.orenodes.common.world.block.NativeOreBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ONBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MOD_ID);

    public static final Supplier<Block> RAW_NATIVE_GOLD_ORE = registerBlock("raw_native_gold_ore", () -> new NativeOreBlock(blockProperties().mapColor(MapColor.GOLD).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F, 5.0F)));
    public static final Supplier<Block> RAW_NATIVE_HEMATITE_ORE = registerBlock("raw_native_hematite_ore", () -> new NativeOreBlock(blockProperties().mapColor(MapColor.CRIMSON_STEM).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F, 5.0F)));
    public static final Supplier<Block> RAW_NATIVE_MALACHITE_ORE = registerBlock("raw_native_malachite_ore", () -> new NativeOreBlock(blockProperties().mapColor(MapColor.DIAMOND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F, 5.0F)));
    public static final Supplier<Block> RAW_NATIVE_SPHALERITE_ORE = registerBlock("raw_native_sphalerite_ore", () -> new NativeOreBlock(blockProperties().mapColor(MapColor.CRIMSON_STEM).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F, 5.0F)));
    public static final Supplier<Block> RAW_NATIVE_WOLFRAMITE_ORE = registerBlock("raw_native_wolframite_ore", () -> new NativeOreBlock(blockProperties().mapColor(MapColor.CRIMSON_STEM).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F, 5.0F)));

    private static BlockBehaviour.Properties blockProperties() {
        return BlockBehaviour.Properties.of();
    }

    private static <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> block) {
        return registerBlock(name, block, true);
    }

    private static <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> block, boolean registerItem) {
        Supplier<T> deferredBlock = BLOCKS.register(name, block);
        if (registerItem)
            ONItems.registerBlockItem(name, deferredBlock);

        return deferredBlock;
    }

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }
}
