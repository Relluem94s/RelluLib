package de.relluem94.rellulib.stores;

public interface ITrippleStore<T, T2, T3> extends IDoubleStore<T, T2>{
    T3 getThirdValue();
    void setThirdValue(T3 thirdValue);
}