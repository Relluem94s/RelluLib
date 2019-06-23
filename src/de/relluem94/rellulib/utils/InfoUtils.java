package de.relluem94.rellulib.utils;

public class InfoUtils {
	public static String OSname() {
        return System.getProperty("os.name");
    }

    public static String OSversion() {
        return System.getProperty("os.version");
    }

    public static String OsArch() {    	
        return System.getProperty("os.arch");
    }	
    
    public static String CPU_Identifer(){
    	return System.getenv("PROCESSOR_IDENTIFIER");
    }
    
    public static String CPU_Architecture(){
    	return System.getenv("PROCESSOR_ARCHITECTURE");
    }
    
    public static String CPU_NumberOfCores(){
    	return System.getenv("NUMBER_OF_PROCESSORS");
    }
   
    public static String JAVA_Name(){
      	 return System.getProperty("java.vm.name");
    }
       
    public static String JAVA_Version(){
    	return System.getProperty("java.vm.version");
    }
    
    public static String USER_home(){
    	return System.getProperty("user.home");
    }
}
