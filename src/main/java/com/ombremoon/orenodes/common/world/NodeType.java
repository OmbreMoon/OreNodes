package com.ombremoon.orenodes.common.world;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

public enum NodeType implements StringRepresentable {
    GOLD("gold"),
    HEMATITE("hematite"),
    MALACHITE("malachite"),
    SPHALERITE("sphalerite"),
    WOLFRAMITE("wolframite");

    public static final Codec<NodeType> CODEC = StringRepresentable.fromEnum(NodeType::values);
    private final String name;

    NodeType(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
