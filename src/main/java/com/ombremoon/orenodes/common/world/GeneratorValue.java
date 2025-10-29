package com.ombremoon.orenodes.common.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;

public record GeneratorValue(BlockState blockState, long breakTime) {
    public static final Codec<GeneratorValue> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    BlockState.CODEC.fieldOf("blockState").forGetter(generatorValue -> generatorValue.blockState),
                    Codec.LONG.fieldOf("breakTime").forGetter(generatorValue -> generatorValue.breakTime)
            ).apply(instance, GeneratorValue::new)
    );
}
