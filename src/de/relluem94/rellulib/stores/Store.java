package de.relluem94.rellulib.stores;

public class Store implements IStore {

    private Object value;

    public Store(Object value) {
        if (value == null) {
            return;
        }

        this.value = value;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String toString() {
        return "Value: " + this.value;
    }
}
