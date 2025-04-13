package de.relluem94.rellulib.vector;

public record Vector1<T extends Number>(T x) implements IVector<T> {

    @Override
    public String toShortString() {
        return x() + "";
    }

    @Override
    public String toString() {
        return "x: " + x();
    }
}