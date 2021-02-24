package de.relluem94.rellulib.stores;

import java.util.Objects;

public class TrippleStore implements IStore {

    private Object value;
    private Object svalue;
    private Object tvalue;

    public TrippleStore(Object value, Object secondvalue, Object thirdvalue) {
        if (value == null) {
            return;
        }

        this.value = value;

        if (secondvalue == null) {
            return;
        }

        this.svalue = secondvalue;

        if (thirdvalue == null) {
            return;
        }

        this.tvalue = thirdvalue;
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

    public void setThirdValue(Object value) {
        this.tvalue = value;
    }

    public Object getThirdValue() {
        return this.tvalue;
    }

    @Override
    public String toString() {
        return "First Value: " + this.value + "\nSecond Value: " + this.svalue + "\nThird Value: " + this.tvalue;
    }
    
    @Override
    public int hashCode(){
        return (int) 94 * value.hashCode() * svalue.hashCode() * tvalue.hashCode();
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
        final TrippleStore other = (TrippleStore) obj;
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        if (!Objects.equals(this.svalue, other.svalue)) {
            return false;
        }
        return Objects.equals(this.tvalue, other.tvalue);
    }
}
