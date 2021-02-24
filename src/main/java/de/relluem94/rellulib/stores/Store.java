package de.relluem94.rellulib.stores;

import java.util.Objects;

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
    
    @Override
    public int hashCode(){
        return (int) 69 * value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Store other = (Store) obj;
        return Objects.equals(this.value, other.value);
    }
}
