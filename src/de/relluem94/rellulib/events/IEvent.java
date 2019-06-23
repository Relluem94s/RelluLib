package de.relluem94.rellulib.events;

import de.relluem94.rellulib.ID;

public interface IEvent {
	public void update();

	public ID getID();

	public void setID(int id);
	
	public boolean isCanceled();
}
