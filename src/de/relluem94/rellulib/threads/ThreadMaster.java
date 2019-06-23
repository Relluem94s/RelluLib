package de.relluem94.rellulib.threads;

import java.util.ArrayList;
import java.util.List;

public class ThreadMaster {
	
	private List<ThreadWorker> threads;

	
	public ThreadMaster(){
		threads = new ArrayList<ThreadWorker>();
	}
	
	
	public int addThread(ThreadWorker worker){
		threads.add(worker);
		return (threads.size()-1);
	}
	
	public boolean startTread(int id){
		if(id != -1){
			if(threads.get(id) != null){
				if(threads.get(id).getThread().isAlive()){
					return false;
				}
				else{
					threads.get(id).start();
					return true;
				}
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	public Thread getThread(int id){
		return threads.get(id).getThread();
	}
	
	
}
