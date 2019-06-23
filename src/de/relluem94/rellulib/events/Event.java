package de.relluem94.rellulib.events;

import de.relluem94.rellulib.ID;

public abstract class Event implements IEvent {

	private boolean isCanceled = false;
	private ID id = new ID();

	@Override
	public boolean isCanceled() {
		return isCanceled;
	}

	@Override
	public ID getID() {
		return id;
	}

	@Override
	public void setID(int id) {
		this.id.setID(id);
	}

	public void setCanceled(boolean setCanceled) {
		this.isCanceled = setCanceled;
	}
}
