package de.relluem94.rellulib.vector;

public record Vector4<T extends Number>(T x, T y, T z, T w) implements IVector<T> {

    @Override
    public String toShortString() {
        return x() + ", " + y() + ", " + z() + ", " + w();
    }

    @Override
    public String toString() {
        return "x: " + x() + ", y: " + y() + ", z: " + z() + ", w: " + w();
    }
}