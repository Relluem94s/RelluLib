package de.relluem94.rellulib.stores;

import java.util.Objects;

public class DoubleStore extends Store implements IDoubleStore {

    private Object secondValue;

    public DoubleStore(Object value, Object secondValue) {
        super(value);

        if (secondValue == null) {
            return;
        }

        this.secondValue = secondValue;
    }

    @Override
    public void setSecondValue(Object value) {
        this.secondValue = value;
    }

    @Override
    public Object getSecondValue() {
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
        final DoubleStore other = (DoubleStore) obj;
        if (!Objects.equals(this.getValue(), other.getValue())) {
            return false;
        }
        return Objects.equals(this.getSecondValue(), other.getSecondValue());
    }
}
