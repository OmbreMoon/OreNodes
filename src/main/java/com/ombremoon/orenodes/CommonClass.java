package com.ombremoon.orenodes;

import com.ombremoon.orenodes.common.init.ONBlocks;
import com.ombremoon.orenodes.common.init.ONItems;
import com.ombremoon.orenodes.common.init.ONStructures;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.loading.FMLLoader;

public class CommonClass {

    public static void init(IEventBus modEventBus) {
        ONBlocks.register(modEventBus);
        ONItems.register(modEventBus);
        ONStructures.register(modEventBus);
    }

    public static boolean isDevEnv() {
        return !FMLLoader.isProduction();
    }

    public static ResourceLocation customLocation(String name) {
        return ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name);
    }
}
