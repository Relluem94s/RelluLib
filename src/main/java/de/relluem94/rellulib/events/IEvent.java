package de.relluem94.rellulib.events;

import de.relluem94.rellulib.ID;

public interface IEvent {

    void update();

    ID getID();

    void setID(int id);

    boolean isCanceled();

    void setCanceled(boolean setCanceled);
}
