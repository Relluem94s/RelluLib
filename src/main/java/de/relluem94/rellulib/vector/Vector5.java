package de.relluem94.rellulib.vector;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public record Vector5<T extends Number>(T x, T y, T z, T w, T v) implements IVector<T> {

    @Contract(pure = true)
    @Override
    public @NotNull String toShortString() {
        return x() + ", " + y() + ", " + z() + ", " + w() + ", " + v();
    }

    @Override
    public @NotNull String toString() {
        return "x: " + x() + ", y: " + y() + ", z: " + z() + ", w: " + w() + ", v: " + v();
    }
}