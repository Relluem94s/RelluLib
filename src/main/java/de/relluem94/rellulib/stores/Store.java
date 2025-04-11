package de.relluem94.rellulib.stores;

import java.util.Objects;

public class Store<T> implements IStore<T> {

    private T value;

    public Store(T value) {
        if (value == null) {
            return;
        }

        this.value = value;
    }

    @Override
    public T getValue() {
        return this.value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Value: " + this.value;
    }
    
    @Override
    public int hashCode(){
        return 69 * value.hashCode();
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
        final Store<?> other = (Store<?>) obj;
        return Objects.equals(this.getValue(), other.getValue());
    }
}
