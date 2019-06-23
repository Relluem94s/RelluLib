package de.relluem94.rellulib.utils;

public class MessureUtils {
private long startTime, endTime, completeTime;
	
	public void startTimer(){
		startTime = System.currentTimeMillis();
	}
	
	public void stopTimer(){
		endTime = System.currentTimeMillis();
		completeTime = completeTime + (endTime - startTime);
	}
	
	public long getDuration(){
		return (endTime - startTime); 
	}
	
	public long getCompleteDuration(){
		return completeTime;
	}
}
