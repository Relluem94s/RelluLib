package de.relluem94.rellulib.vector;

public interface IVector<T extends Number> {

    String toString();
    String toShortString();

    void set(T number);
    T get();
}
