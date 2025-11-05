package com.ombremoon.orenodes.common.world.block;

import com.ombremoon.orenodes.common.init.ONBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BuddingAmethystBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class NativeRedstoneBlock extends NativeOreBlock {

    public NativeRedstoneBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        pLevel.scheduleTick(pPos, this, 50);
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        Direction direction = Direction.UP;
        BlockPos blockpos = pPos.relative(direction);
        BlockState blockstate = pLevel.getBlockState(blockpos);
        Block block = null;
        boolean flag = true;
        if (BuddingAmethystBlock.canClusterGrowAtState(blockstate)) {
            block = ONBlocks.REDSTONE_BUD.get();
        } else if (blockstate.is(ONBlocks.REDSTONE_BUD.get()) && blockstate.getValue(RedstoneClusterBlock.FACING) == direction) {
            block = ONBlocks.REDSTONE_BULB.get();
            flag = false;
        }

        if (block != null) {
            BlockState blockstate1 = block.defaultBlockState()
                    .setValue(RedstoneClusterBlock.FACING, direction)
                    .setValue(RedstoneClusterBlock.WATERLOGGED, Boolean.valueOf(blockstate.getFluidState().getType() == Fluids.WATER));
            pLevel.setBlockAndUpdate(blockpos, blockstate1);

            if (flag)
                pLevel.scheduleTick(pPos, this, 50);
        }
    }
}
