package de.relluem94.rellulib.vector;

public record Vector3<T extends Number>(T x, T y, T z) implements IVector<T> {

    @Override
    public String toShortString() {
        return x() + ", " + y() + ", " + z();
    }

    @Override
    public String toString() {
        return "x: " + x() + ", y: " + y() + ", z: " + z();
    }
}