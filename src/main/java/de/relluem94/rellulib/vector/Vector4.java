package de.relluem94.rellulib.vector;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public record Vector4<T extends Number>(T x, T y, T z, T w) implements IVector<T> {

    @Contract(pure = true)
    @Override
    public @NotNull String toShortString() {
        return x() + ", " + y() + ", " + z() + ", " + w();
    }

    @Override
    public @NotNull String toString() {
        return "x: " + x() + ", y: " + y() + ", z: " + z() + ", w: " + w();
    }
}