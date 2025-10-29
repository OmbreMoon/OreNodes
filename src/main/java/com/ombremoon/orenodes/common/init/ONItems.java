package com.ombremoon.orenodes.common.init;

import com.ombremoon.orenodes.Constants;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ONItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);

    public static Supplier<Item> RAW_NATIVE_GOLD = registerSimpleItem("raw_native_gold");
    public static Supplier<Item> RAW_NATIVE_HEMATITE = registerSimpleItem("raw_native_hematite");
    public static Supplier<Item> RAW_NATIVE_MALACHITE = registerSimpleItem("raw_native_malachite");
    public static Supplier<Item> RAW_NATIVE_SPHALERITE = registerSimpleItem("raw_native_sphalerite");
    public static Supplier<Item> RAW_NATIVE_WOLFRAMITE = registerSimpleItem("raw_native_wolframite");

    public static Supplier<Item> registerSimpleItem(String name) {
        return registerItem(name, () -> new Item(getItemProperties()));
    }

    public static Supplier<Item> registerItem(String name, Supplier<Item> itemSupplier) {
        return ITEMS.register(name, itemSupplier);
    }

    public static Supplier<Item> registerBlockItem(String name, Supplier<? extends Block> block) {
        Supplier<Item> item = ITEMS.register(name, () -> new BlockItem(block.get(), getItemProperties()));
        return item;
    }

    public static Item.Properties getItemProperties() {
        return new Item.Properties();
    }

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
