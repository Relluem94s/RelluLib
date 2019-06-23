package de.relluem94.rellulib.events;

import de.relluem94.rellulib.FixedSizeList;
import de.relluem94.rellulib.ID;

public class EventExecutor{
	
	private FixedSizeList<IEvent> events;
	
	public EventExecutor(){
		events = new FixedSizeList<IEvent>(100);
	}
	
	public EventExecutor(int eventAmount){
		events = new FixedSizeList<IEvent>(eventAmount);
	}

	public boolean registerEvent(IEvent e){
		for(int i = 0; i < events.size(); i++){
			if(events.get(i) == null){
				events.set(i, e);
				events.get(i).setID(i);
				return false;
			}
		}
		return false;
	}
	
	public void cleanUp(){
		for(int i = 0; i < events.size(); i++){
			events.set(i, null);
		}
	}

	/**
	 * Put this in a forloop
	 * 
	 * 
	 */
	public void executeEvents(){
		IEvent e;
		for(int i = 0; i < events.size(); i++){
			if(events.get(i) != null){
				e = events.get(i);
				if(!e.isCanceled()){
					e.update();
				}
			}
		}
	}

	public void removeEvent(ID id) {
		//events.remove(e);
		events.set(id.getID(), null);
	}
	
	public void removeEvent(IEvent e) {
		//events.remove(e);
		events.set(e.getID().getID(), null);
	}
	
	public IEvent getEvent(ID id){
		return events.get(id.getID());
	}
	
	public IEvent getEvent(IEvent e){
		return events.get(e.getID().getID());
	}
	
	/**
	 * 
	 * @return Amount of Events in Event List
	 */
	public int getEventsAmount(){
		int a = 0;
		for(int i = 0; i < events.size(); i++){
			if(events.get(i) != null){
				a++;
			}
		}
		return a;
	}
	
	/**
	 * 
	 * @return Free Size of Event List
	 */
	public int getEventsFreeAmount(){
		return events.size() - getEventsAmount();
	}
	
	
	/**
	 * 
	 * @return Size of Event List
	 */
	public int getEventsSizeAmount(){
		return events.size() ;
	}
}
