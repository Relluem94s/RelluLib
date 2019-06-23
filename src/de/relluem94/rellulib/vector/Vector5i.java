package de.relluem94.rellulib.vector;

import java.text.DecimalFormat;

public class Vector5i implements IVector{

    public int x;

	public int y;

	public int z;
	
	public int w;
	
	public int v;

	public Vector5i(int x, int y, int z, int w, int v){
		setTo(x, y, z, w, v);
	}
	
    public Vector5i(Vector5i v){
        setTo(v);
    }

    public Vector5i(){
		setZero();
	}
    
	public void setTo(Vector5i v){
        this.x = v.x;
	    this.y = v.y;
       	this.z = v.z;
       	this.w = v.w;
       	this.v = v.v;
    }
    
    public void setTo(int x, int y, int z, int w, int v){
       	this.x = x;
	    this.y = y;
       	this.z = z;
       	this.w = w;
       	this.v = v;
	}
    
    public void setZero(){
    	this.x = 0;
		this.y = 0;
		this.z = 0;
		this.w = 0;
		this.v = 0;
    }
    
    public void negate(){
    	this.x = - this.x;
		this.y = - this.y;
		this.z = - this.z;
		this.w = - this.w;
		this.v = - this.v;
    }
  
    @Override
    public String toString(){
    	return new String("X:" + x + " Y:" + y + " Z:" + z + " W:" + w + " V:" + v);
    }
    
    @Override
    public String toShortString(){
    	return "X:" + new DecimalFormat("##.##").format(x) + " Y:" + new DecimalFormat("##.##").format(y) + 
    		  " Z:" + new DecimalFormat("##.##").format(z) + " W:" + new DecimalFormat("##.##").format(w) + 
    		  " V:" + new DecimalFormat("##.##").format(v);
    }
    
    @Override
    public String toListString(){
		return "" + x + "," + y + "," + z + "," + w + "," + v;
	}

}  