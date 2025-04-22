package de.relluem94.rellulib.stores;

import java.util.Objects;

public class TrippleStore<T, T2, T3> extends DoubleStore<T, T2> implements ITrippleStore<T, T2, T3> {

    private T3 thirdValue;

    public TrippleStore(T value, T2 secondValue, T3 thirdValue) {
        super(value, secondValue);
        if (value == null) {
            return;
        }

        if (thirdValue == null) {
            return;
        }

        this.thirdValue = thirdValue;
    }

    public void setThirdValue(T3 thirdValue) {
        this.thirdValue = thirdValue;
    }

    public T3 getThirdValue() {
        return this.thirdValue;
    }

    @Override
    public String toString() {
        return "First Value: " + getValue() + "\nSecond Value: " + getSecondValue() + "\nThird Value: " + this.thirdValue;
    }
    
    @Override
    public int hashCode(){
        return 94 * getValue().hashCode() * getSecondValue().hashCode() * Objects.hashCode(thirdValue);
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
        final TrippleStore<?,?,?> other = (TrippleStore<?,?,?>) obj;
        if (!Objects.equals(this.getValue(), other.getValue())) {
            return false;
        }
        if (!Objects.equals(this.getSecondValue(), other.getSecondValue())) {
            return false;
        }
        return Objects.equals(this.getThirdValue(), other.getThirdValue());
    }
}
