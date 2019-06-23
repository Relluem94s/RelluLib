package de.relluem94.rellulib.stores;

public class DoubleStore implements IStore{
	
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
	
	public String toString(){
		return "First Value: " + this.value + "\nSecond Value: " + this.svalue;
	}
}
