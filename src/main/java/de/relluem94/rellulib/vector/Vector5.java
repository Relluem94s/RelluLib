package de.relluem94.rellulib.vector;

public record Vector5<T extends Number>(T x, T y, T z, T w, T v) implements IVector<T> {

    @Override
    public String toShortString() {
        return x() + ", " + y() + ", " + z() + ", " + w() + ", " + v();
    }

    @Override
    public String toString() {
        return "x: " + x() + ", y: " + y() + ", z: " + z() + ", w: " + w() + ", v: " + v();
    }
}