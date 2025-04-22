package de.relluem94.rellulib.vector;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public record Vector3<T extends Number>(T x, T y, T z) implements IVector<T> {

    @Contract(pure = true)
    @Override
    public @NotNull String toShortString() {
        return x() + ", " + y() + ", " + z();
    }

    @Override
    public @NotNull String toString() {
        return "x: " + x() + ", y: " + y() + ", z: " + z();
    }
}