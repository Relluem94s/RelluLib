package de.relluem94.rellulib.stores;

public interface ITrippleStore extends IDoubleStore{
    Object getThirdValue();
    void setThirdValue(Object value);
}