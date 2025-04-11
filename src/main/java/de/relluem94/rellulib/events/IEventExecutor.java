package de.relluem94.rellulib.events;

import de.relluem94.rellulib.ID;
import de.relluem94.rellulib.exceptions.EventException;

public interface IEventExecutor {
    void registerEvent(IEvent event);

    void cleanUp();

    int executeEvents();

    void removeEvent(ID id);

    void removeEvent(IEvent event);

    IEvent getEvent(ID id) throws EventException;

    IEvent getEvent(IEvent event) throws EventException;

    int getEventsAmount();

    int getEventsFreeAmount();

    int getEventsSizeAmount();
}
