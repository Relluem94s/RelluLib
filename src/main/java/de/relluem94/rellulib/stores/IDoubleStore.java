package de.relluem94.rellulib.stores;

public interface IDoubleStore<T, T2> extends IStore<T>{
    T2 getSecondValue();
    void setSecondValue(T2 secondValue);
}