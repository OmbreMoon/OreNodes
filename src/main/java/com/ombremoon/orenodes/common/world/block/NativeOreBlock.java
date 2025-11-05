package com.ombremoon.orenodes.common.world.block;

import com.ombremoon.orenodes.common.init.ONBlocks;
import com.ombremoon.orenodes.common.world.GeneratorData;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class NativeOreBlock extends Block {
    public NativeOreBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if (!level.isClientSide() && this != ONBlocks.RAW_NATIVE_REDSTONE_ORE.get()) {
            ServerLevel serverLevel = (ServerLevel) level;
            GeneratorData data = GeneratorData.get(serverLevel);

            if (player != null && !player.getAbilities().instabuild)
                data.cacheGenerator(serverLevel, pos, state);
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }
}
