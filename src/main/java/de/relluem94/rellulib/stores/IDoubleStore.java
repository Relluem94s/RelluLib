package de.relluem94.rellulib.stores;

public interface IDoubleStore extends IStore{
    Object getSecondValue();
    void setSecondValue(Object value);
}