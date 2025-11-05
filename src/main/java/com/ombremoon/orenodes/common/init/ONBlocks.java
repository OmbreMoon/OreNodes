package com.ombremoon.orenodes.common.init;

import com.ombremoon.orenodes.Constants;
import com.ombremoon.orenodes.common.world.block.NativeOreBlock;
import com.ombremoon.orenodes.common.world.block.NativeRedstoneBlock;
import com.ombremoon.orenodes.common.world.block.RedstoneClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ONBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MOD_ID);

    public static final Supplier<Block> RAW_NATIVE_GOLD_ORE = registerBlock("raw_native_gold_ore", () -> new NativeOreBlock(blockProperties().mapColor(MapColor.GOLD).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F, 5.0F).pushReaction(PushReaction.BLOCK)));
    public static final Supplier<Block> RAW_NATIVE_HEMATITE_ORE = registerBlock("raw_native_hematite_ore", () -> new NativeOreBlock(blockProperties().mapColor(MapColor.CRIMSON_STEM).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F, 5.0F).pushReaction(PushReaction.BLOCK)));
    public static final Supplier<Block> RAW_NATIVE_MALACHITE_ORE = registerBlock("raw_native_malachite_ore", () -> new NativeOreBlock(blockProperties().mapColor(MapColor.WARPED_NYLIUM).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F, 5.0F).pushReaction(PushReaction.BLOCK)));
    public static final Supplier<Block> RAW_NATIVE_SPHALERITE_ORE = registerBlock("raw_native_sphalerite_ore", () -> new NativeOreBlock(blockProperties().mapColor(MapColor.DIAMOND).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F, 5.0F).pushReaction(PushReaction.BLOCK)));
    public static final Supplier<Block> RAW_NATIVE_WOLFRAMITE_ORE = registerBlock("raw_native_wolframite_ore", () -> new NativeOreBlock(blockProperties().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F, 5.0F).pushReaction(PushReaction.BLOCK)));
    public static final Supplier<Block> RAW_NATIVE_COAL_ORE = registerBlock("raw_native_coal_ore", () -> new NativeOreBlock(blockProperties().mapColor(MapColor.COLOR_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F, 5.0F).pushReaction(PushReaction.BLOCK)));
    public static final Supplier<Block> RAW_NATIVE_REDSTONE_ORE = registerBlock("raw_native_redstone_ore", () -> new NativeRedstoneBlock(blockProperties().mapColor(MapColor.COLOR_RED).randomTicks().instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F, 5.0F).pushReaction(PushReaction.BLOCK)));
    public static final Supplier<Block> RAW_NATIVE_ANDESITE = registerBlock("raw_native_andesite", () -> new NativeOreBlock(blockProperties().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F, 5.0F).pushReaction(PushReaction.BLOCK)));
    public static final Supplier<Block> SCARLETINE = registerSimpleBlock("scarletine", blockProperties().mapColor(MapColor.FIRE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F, 5.0F));

    public static final Supplier<Block> RAW_GOLD_BLOCK = registerSimpleBlock("raw_native_gold_block", blockProperties().mapColor(MapColor.GOLD).requiresCorrectToolForDrops().strength(5.0F, 5.0F));
    public static final Supplier<Block> RAW_HEMATITE_BLOCK = registerSimpleBlock("raw_hematite_block", blockProperties().mapColor(MapColor.CRIMSON_STEM).requiresCorrectToolForDrops().strength(5.0F, 5.0F));
    public static final Supplier<Block> RAW_MALACHITE_BLOCK = registerSimpleBlock("raw_malachite_block", blockProperties().mapColor(MapColor.WARPED_NYLIUM).requiresCorrectToolForDrops().strength(5.0F, 5.0F));
    public static final Supplier<Block> RAW_SPHALERITE_BLOCK = registerSimpleBlock("raw_sphalerite_block", blockProperties().mapColor(MapColor.DIAMOND).requiresCorrectToolForDrops().strength(5.0F, 5.0F));
    public static final Supplier<Block> RAW_WOLFRAMITE_BLOCK = registerSimpleBlock("raw_wolframite_block", blockProperties().mapColor(MapColor.TERRACOTTA_GRAY).requiresCorrectToolForDrops().strength(5.0F, 5.0F));

    public static final Supplier<Block> REDSTONE_BULB = registerBlock(
            "redstone_bulb", () -> new RedstoneClusterBlock(
                    7.0F, 3.0F, blockProperties()
                    .mapColor(MapColor.COLOR_RED)
                    .randomTicks()
                    .forceSolidOn()
                    .noOcclusion()
                    .strength(1.5F)
                    .sound(SoundType.AMETHYST)
                    .pushReaction(PushReaction.DESTROY)
                    .lightLevel(blockState -> 5)
            )
    );
    public static final Supplier<Block> REDSTONE_BUD = registerBlock("redstone_bud", () -> new RedstoneClusterBlock(3.0F, 4.0F, BlockBehaviour.Properties.copy(REDSTONE_BULB.get())));
    private static BlockBehaviour.Properties blockProperties() {
        return BlockBehaviour.Properties.of();
    }

    private static Supplier<Block> registerSimpleBlock(String name, BlockBehaviour.Properties properties) {
        return registerBlock(name, () -> new Block(properties), true);
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
