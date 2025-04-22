package de.relluem94.rellulib.vector;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public record Vector1<T extends Number>(T x) implements IVector<T> {

    @Contract(pure = true)
    @Override
    public @NotNull String toShortString() {
        return x() + "";
    }

    @Override
    public @NotNull String toString() {
        return "x: " + x();
    }
}