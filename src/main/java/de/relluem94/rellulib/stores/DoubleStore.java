package de.relluem94.rellulib.stores;

import java.util.Objects;

public class DoubleStore<T, T2> extends Store<T> implements IDoubleStore<T, T2> {

    private T2 secondValue;

    public DoubleStore(T value, T2 secondValue) {
        super(value);

        if (secondValue == null) {
            return;
        }

        this.secondValue = secondValue;
    }

    @Override
    public void setSecondValue(T2 secondValue) {
        this.secondValue = secondValue;
    }

    @Override
    public T2 getSecondValue() {
        return this.secondValue;
    }

    @Override
    public String toString() {
        return "First Value: " + getValue() + "\nSecond Value: " + this.secondValue;
    }
    
    @Override
    public int hashCode(){
        return 42 * getValue().hashCode() * secondValue.hashCode();
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
        final DoubleStore<?,?> other = (DoubleStore<?,?>) obj;
        if (!Objects.equals(this.getValue(), other.getValue())) {
            return false;
        }
        return Objects.equals(this.getSecondValue(), other.getSecondValue());
    }
}
