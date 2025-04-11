package de.relluem94.rellulib.stores;

public interface IStore<T> {
    T getValue();
    void setValue(T value);
}