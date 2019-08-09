package de.relluem94.rellulib.test;

import de.relluem94.rellulib.utils.LogUtils;

public class TestClass6{
	
	public static void main(String[] args) {
		logUtilsLogLevelTest();
	}
	
	public static void logUtilsLogLevelTest(){
		LogUtils.setLog2File(true);
		
		LogUtils.error("Test22");
		LogUtils.setError(false);
		LogUtils.error("Test44");
		LogUtils.setError(true);
		LogUtils.error("Test66");
		
		
		LogUtils.info("Test22");
		LogUtils.setInfo(false);
		LogUtils.info("Test44");
		LogUtils.setInfo(true);
		LogUtils.info("Test66");
		
		
		LogUtils.warning("Test22");
		LogUtils.setWarning(false);
		LogUtils.warning("Test44");
		LogUtils.setWarning(true);
		LogUtils.warning("Test66");
		
		LogUtils.log("Test22");
		LogUtils.setOther(false);
		LogUtils.log("Test44");
		LogUtils.setOther(true);
		LogUtils.log("Test66");
	}
	
}
