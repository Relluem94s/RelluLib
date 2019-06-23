package de.relluem94.rellulib.stores;

public class TrippleStore implements IStore{
	
	private Object value;
	private Object svalue;
	private Object tvalue;

    public TrippleStore(Object value, Object secondvalue , Object thirdvalue) {
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

    public Object getValue() {
        return this.value;
    }

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
	
	public String toString(){
		return "First Value: " + this.value + "\nSecond Value: " + this.svalue + "\nThird Value: " + this.tvalue;
	}
}
