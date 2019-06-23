package de.relluem94.rellulib.test;

import de.relluem94.rellulib.ID;
import de.relluem94.rellulib.utils.LogUtils;
import de.relluem94.rellulib.utils.ReflectionUtils;

public class TestClass5{
	
	public static void main(String[] args) {
		reflectionUtils();
	}
	
	public static void reflectionUtils(){
		ID id_test = new ID();
		id_test.setID(27);
		
		ID id_test2 = new ID();
		id_test2.setID(8);
			
		LogUtils.info("ID 1: " + ReflectionUtils.getValue(ReflectionUtils.getMemberField(id_test, "id"), id_test));
		LogUtils.info("ID 2: " + ReflectionUtils.getValue(ReflectionUtils.getMemberField(id_test2, "id"), id_test2));
		ReflectionUtils.setValue(ReflectionUtils.getMemberField(id_test2, "id"), id_test2, 1994);
		LogUtils.info("ID 2: " + id_test2.getID());
	}
	
}
