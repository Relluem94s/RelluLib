package de.relluem94.rellulib.stores;

public class Store implements IStore {

    private Object value;

    public Store(Object value) {
        if (value == null) {
            return;
        }

        this.value = value;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Value: " + this.value;
    }
}
