package com.ombremoon.orenodes.common.events;

import com.ombremoon.orenodes.Constants;
import com.ombremoon.orenodes.common.world.GeneratorData;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID)
public class WorldEvents {

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {
        if (!event.level.isClientSide && event.phase == TickEvent.Phase.END) {
            ServerLevel level = (ServerLevel) event.level;
            GeneratorData data = GeneratorData.get(level);
            data.tickGenerators(level);
        }
    }
}
