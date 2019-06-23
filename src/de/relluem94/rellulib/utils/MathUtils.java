package de.relluem94.rellulib.utils;

import de.relluem94.rellulib.color.Color3f;
import de.relluem94.rellulib.color.Color3i;
import de.relluem94.rellulib.vector.Vector3f;

public class MathUtils {
	public static Integer getRemainder(int dividend, int divisor){
		return dividend % divisor;
	}
	
	public static boolean almostEqual(double a, double b, double eps){
	    return Math.abs(a-b)<eps;
	}
	
	public static boolean isOdd(int number){
		if (number % 2 == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean isEven(int number){
		if (number % 2 == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static int convertInt2Hex(int n) {
		  return Integer.valueOf(String.valueOf(n), 16);
	}
	
	public static Float toFloat(String input){
		if(isFloat(input)){
			float x = Float.parseFloat(input);
			return x;
		}
		else{
			return 0f;
		}
	}
	
	public static Integer toInt(String input){
		if(isInt(input)){
			int x = Integer.parseInt(input);
			return x;
		}
		else{
			return 0;
		}
	}
	
	public static boolean toBoolean(String input){
		boolean x;
		input = input.replaceAll("[\\\t|\\\n|\\\r]", "");
		if(input.equalsIgnoreCase("true")){
			x = true;
		}
		else{
			x = false;
		}
		return x;
	}
	
	public static Vector3f toVector3f(String input){
		String[] vector = input.split(",");
		if(isFloat(vector[0]) && isFloat(vector[1]) && isFloat(vector[2])){
			float x = Float.parseFloat(vector[0]);
			float y = Float.parseFloat(vector[1]);
			float z = Float.parseFloat(vector[2]);
			return new Vector3f(x,y,z);
		}
		else{
			return new Vector3f(0,0,0);
		}
	}
	
	public static Color3f toColor3f(String input){
		String[] color = input.split(",");
		if(isFloat(color[0]) && isFloat(color[1]) && isFloat(color[2])){
			float r = Float.parseFloat(color[0]);
			float g = Float.parseFloat(color[1]);
			float b = Float.parseFloat(color[2]);
			return new Color3f(r,g,b);
		}
		else{
			return new Color3f(0,0,0);
		}
	}
	
	public static Color3i toColor3i(String input){
		String[] color = input.split(",");
		if(isInt(color[0]) && isInt(color[1]) && isInt(color[2])){
			int r = Integer.parseInt(color[0]);
			int g = Integer.parseInt(color[1]);
			int b = Integer.parseInt(color[2]);
			return new Color3i(r,g,b);
		}
		else{
			return new Color3i(0,0,0);
		}
	}
	
	
	
	public static boolean isInt(String s){
		 try{ 
			 @SuppressWarnings("unused")
			 int i = Integer.parseInt(s); return true; 
			 }
		 catch(NumberFormatException er){
			 return false;
			 }
		}
	
	public static boolean isFloat(String s){
		 try{ 
			 @SuppressWarnings("unused")
			 float f = Float.parseFloat(s); return true; 
			 }
		 catch(NumberFormatException er){
			 return false;
		}
	}
}
