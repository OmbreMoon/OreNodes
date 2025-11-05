package com.ombremoon.orenodes.compat.jade;

import com.ombremoon.orenodes.CommonClass;
import com.ombremoon.orenodes.common.init.ONBlocks;
import com.ombremoon.orenodes.common.init.ONItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElement;
import snownee.jade.api.ui.IElementHelper;

public enum OreNodeProvider implements IBlockComponentProvider {
    INSTANCE;

    OreNodeProvider() {
    }

    @Override
    public @Nullable IElement getIcon(BlockAccessor accessor, IPluginConfig config, IElement currentIcon) {
        if (accessor.getBlock() == ONBlocks.RAW_NATIVE_GOLD_ORE.get()) {
            return IElementHelper.get().item(new ItemStack(ONItems.RAW_NATIVE_GOLD.get()));
        } else if (accessor.getBlock() == ONBlocks.RAW_NATIVE_HEMATITE_ORE.get()) {
            return IElementHelper.get().item(new ItemStack(ONItems.RAW_HEMATITE.get()));
        } else if (accessor.getBlock() == ONBlocks.RAW_NATIVE_MALACHITE_ORE.get()) {
            return IElementHelper.get().item(new ItemStack(ONItems.RAW_MALACHITE.get()));
        } else if (accessor.getBlock() == ONBlocks.RAW_NATIVE_SPHALERITE_ORE.get()) {
            return IElementHelper.get().item(new ItemStack(ONItems.RAW_SPHALERITE.get()));
        } else if (accessor.getBlock() == ONBlocks.RAW_NATIVE_WOLFRAMITE_ORE.get()) {
            return IElementHelper.get().item(new ItemStack(ONItems.RAW_WOLFRAMITE.get()));
        } else if (accessor.getBlock() == ONBlocks.RAW_NATIVE_ANDESITE.get()) {
            return IElementHelper.get().item(new ItemStack(Items.ANDESITE));
        } else if (accessor.getBlock() == ONBlocks.RAW_NATIVE_REDSTONE_ORE.get()) {
            return IElementHelper.get().item(new ItemStack(ONBlocks.RAW_NATIVE_REDSTONE_ORE.get()));
        } else {
            return accessor.getBlock() == ONBlocks.RAW_NATIVE_COAL_ORE.get() ? IElementHelper.get().item(new ItemStack(Items.COAL)) : null;
        }
    }

    @Override
    public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
    }

    @Override
    public ResourceLocation getUid() {
        return OreNodePlugin.ORE_NODE;
    }
}
