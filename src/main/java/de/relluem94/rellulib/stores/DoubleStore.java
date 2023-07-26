package de.relluem94.rellulib.stores;

import java.util.Objects;

public class DoubleStore implements IStore {

    private Object value;
    private Object svalue;

    public DoubleStore(Object value, Object secondvalue) {
        if (value == null) {
            return;
        }

        this.value = value;

        if (secondvalue == null) {
            return;
        }

        this.svalue = secondvalue;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }

    public void setSecondValue(Object value) {
        this.svalue = value;
    }

    public Object getSecondValue() {
        return this.svalue;
    }

    @Override
    public String toString() {
        return "First Value: " + this.value + "\nSecond Value: " + this.svalue;
    }
    
    @Override
    public int hashCode(){
        return 42 * value.hashCode() * svalue.hashCode();
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
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return Objects.equals(this.svalue, other.svalue);
    }
}
