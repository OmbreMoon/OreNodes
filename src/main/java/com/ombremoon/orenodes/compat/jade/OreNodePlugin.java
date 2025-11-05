package com.ombremoon.orenodes.compat.jade;


import com.ombremoon.orenodes.CommonClass;
import com.ombremoon.orenodes.common.world.block.NativeOreBlock;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class OreNodePlugin implements IWailaPlugin {
    public static final ResourceLocation ORE_NODE = CommonClass.customLocation("ore_node");

    @Override
    public void register(IWailaCommonRegistration registration) {
        IWailaPlugin.super.register(registration);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockIcon(OreNodeProvider.INSTANCE, NativeOreBlock.class);
        registration.markAsClientFeature(ORE_NODE);
    }
}
