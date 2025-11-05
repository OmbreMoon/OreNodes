package com.ombremoon.orenodes.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.ombremoon.orenodes.common.init.ONBlocks;
import com.ombremoon.orenodes.common.world.GeneratorData;
import com.ombremoon.orenodes.common.world.block.NativeOreBlock;
import com.simibubi.create.foundation.utility.BlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.function.Consumer;

@Mixin(BlockHelper.class)
public class BlockHelperMixin {

    @Inject(method = "destroyBlockAs(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;FLjava/util/function/Consumer;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;spawnAfterBreak(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/item/ItemStack;Z)V"))
    private static void destroyBlockAsInjection(Level world, BlockPos pos, @Nullable Player player, ItemStack usedTool,
                                                 float effectChance, Consumer<ItemStack> droppedItemCallback, CallbackInfo ci, @Local BlockState state) {
        if (!world.isClientSide() && state.getBlock() instanceof NativeOreBlock && !state.is(ONBlocks.RAW_NATIVE_REDSTONE_ORE.get())) {
            ServerLevel serverLevel = (ServerLevel) world;
            GeneratorData data = GeneratorData.get(serverLevel);
            data.cacheGenerator(serverLevel, pos, state);
        }
    }
}
