package com.ombremoon.orenodes.common.init;

import com.ombremoon.orenodes.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ONItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Constants.MOD_ID);

    public static Supplier<Item> RAW_NATIVE_GOLD = registerSimpleItem("raw_native_gold");
    public static Supplier<Item> RAW_HEMATITE = registerSimpleItem("raw_hematite");
    public static Supplier<Item> RAW_MALACHITE = registerSimpleItem("raw_malachite");
    public static Supplier<Item> RAW_SPHALERITE = registerSimpleItem("raw_sphalerite");
    public static Supplier<Item> RAW_WOLFRAMITE = registerSimpleItem("raw_wolframite");
    public static Supplier<Item> REDSTONE = registerSimpleItem("redstone");
    public static Supplier<Item> CRUSHED_ASURINE = registerSimpleItem("crushed_asurine");
    public static Supplier<Item> CRUSHED_CRIMSITE = registerSimpleItem("crushed_crimsite");
    public static Supplier<Item> CRUSHED_OCHRUM = registerSimpleItem("crushed_ochrum");
    public static Supplier<Item> CRUSHED_TUFF = registerSimpleItem("crushed_tuff");
    public static Supplier<Item> CRUSHED_VERIDIUM = registerSimpleItem("crushed_veridium");
    public static Supplier<Item> CRUSHED_RAW_TUNGSTEN = registerSimpleItem("crushed_raw_tungsten");
    public static Supplier<Item> TUNGSTEN_INGOT = registerSimpleItem("tungsten_ingot");
    public static Supplier<Item> TUNGSTEN_NUGGET = registerSimpleItem("tungsten_nugget");

    public static Supplier<CreativeModeTab> ORE_NODES = CREATIVE_MODE_TABS.register("ore_nodes", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(RAW_NATIVE_GOLD.get()))
            .title(Component.translatable("itemGroup.orenodes.tab"))
            .displayItems((display, output) -> ITEMS.getEntries().forEach(object -> output.accept(new ItemStack(object.get()))))
            .build());

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
        CREATIVE_MODE_TABS.register(modEventBus);
    }
}
