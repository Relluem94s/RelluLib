package de.relluem94.rellulib.vector;

public record Vector2<T extends Number>(T x, T y) implements IVector<T> {

    @Override
    public String toShortString() {
        return x() + ", " + y();
    }

    @Override
    public String toString() {
        return "x: " + x() + ", y: " + y();
    }
}