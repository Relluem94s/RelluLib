package de.relluem94.rellulib.vector;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public record Vector2<T extends Number>(T x, T y) implements IVector<T> {

    @Contract(pure = true)
    @Override
    public @NotNull String toShortString() {
        return x() + ", " + y();
    }

    @Override
    public @NotNull String toString() {
        return "x: " + x() + ", y: " + y();
    }
}