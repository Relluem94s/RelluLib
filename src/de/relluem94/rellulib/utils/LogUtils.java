package de.relluem94.rellulib.utils;

import java.util.List;

public class LogUtils {
	
	/**
	 *  Gibt ein Objekt aus <br>
	 * 
	 */
	public static void log(Object o){
		if(LOGUTILS_LEVEL_OTHER){
			System.out.println("\t\t" + o);
		}
	}
	
	/**
	 *  Gibt ein Objekt ohne Zeilenumbruch aus<br>
	 * 
	 */
	public static void logInLine(Object o){
		if(LOGUTILS_LEVEL_OTHER){
			System.out.print(o);
		}
	}
	
	/**
	 *  Gibt ein Objekt aus <br>
	 *  Aber nur wenn true <br>
	 */
	public static void debug(Object o, boolean shouldlog){
		if(LOGUTILS_LEVEL_OTHER){
			if(shouldlog){
				System.out.println("\t\t" + o);
			}
		}
	}
	
	/**
	 *  Gibt Objekt ohne Zeilenumbruch aus <br>
	 * 	Aber nur wenn true <br>
	 */
	public static void debugInLine(Object o, boolean shouldlog){
		if(LOGUTILS_LEVEL_OTHER){
			if(shouldlog){
				System.out.print(o);
			}
		}
	}
	
	/**
	 *  Gibt eine Objekt aus <br>
	 *  Mit ERROR davor <br>
	 */
	public static void error(Object o){
		if(LOGUTILS_LEVEL_ERROR){
			System.out.println("[ERROR]\t\t" + o);
		}
		
	}
	
	/**
	 *  Gibt eine Objekt aus <br>
	 *  Mit INFO davor <br>
	 */
	public static void info(Object o){
		if(LOGUTILS_LEVEL_INFO){
			System.out.println("[INFO]\t\t" + o);
		}
		
	}
	
	/**
	 *  Gibt eine Objekt aus <br>
	 *  Mit WARNING davor <br>
	 */
	public static void warning(Object o){
		if(LOGUTILS_LEVEL_WARNING){
			System.out.println("[WARNING]\t" + o);
		}
	}
	
	/**
	 *  Gibt Objekt ohne Zeilenumbruch aus <br>
	 * 
	 */
	public static void line(Object o){
		if(LOGUTILS_LEVEL_OTHER){
			System.out.print(o);
		}
	}
	
	public static void list(List<?> input){
		if(LOGUTILS_LEVEL_OTHER){
			for(Object o : input){
				log(o);
			}
		}
	}
	
	public static void intArray(Integer[] input){
		if(LOGUTILS_LEVEL_OTHER){
			for(int i : input){
				log(i);
			}
		}
	}
	
	public static void stringArray(String[] input){
		if(LOGUTILS_LEVEL_OTHER){
			for(String s : input){
				log(s);
			}
		}
	}
	
	
	private static boolean LOGUTILS_LEVEL_OTHER = true;
	private static boolean LOGUTILS_LEVEL_INFO = true;
	private static boolean LOGUTILS_LEVEL_WARNING = true;
	private static boolean LOGUTILS_LEVEL_ERROR = true;
	
	public static void setInfo(boolean enable){
		LogUtils.LOGUTILS_LEVEL_INFO = enable;
	}
	
	public static boolean getInfo(){
		return LogUtils.LOGUTILS_LEVEL_WARNING;
	}
	
	public static void setWarning(boolean enable){
		LogUtils.LOGUTILS_LEVEL_WARNING = enable;
	}
	
	public static boolean getWarning(){
		return LogUtils.LOGUTILS_LEVEL_WARNING;
	}
	
	public static void setError(boolean enable){
		LogUtils.LOGUTILS_LEVEL_ERROR = enable;
	}
	
	public static boolean getError(){
		return LogUtils.LOGUTILS_LEVEL_ERROR;
	}
	
	public static void setOther(boolean enable){
		LogUtils.LOGUTILS_LEVEL_OTHER = enable;
	}
	
	public static boolean getOther(){
		return LogUtils.LOGUTILS_LEVEL_OTHER;
	}
}
