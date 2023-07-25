package de.relluem94.rellulib.events;

import de.relluem94.rellulib.FixedSizeList;
import de.relluem94.rellulib.ID;
import de.relluem94.rellulib.exceptions.EventException;

public class EventExecutor {

    private FixedSizeList<IEvent> events;

    public EventExecutor() {
        events = new FixedSizeList<>(100);
    }

    public EventExecutor(int eventAmount) {
        events = new FixedSizeList<>(eventAmount);
    }

    public boolean registerEvent(IEvent e) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i) == null) {
                events.set(i, e);
                events.get(i).setID(i);
                return false;
            }
        }
        return false;
    }

    public void cleanUp() {
        for (int i = 0; i < events.size(); i++) {
            events.set(i, null);
        }
    }

    /**
     * Put this in a loop in a thread
     *
     *
     */
    public void executeEvents() {
        IEvent e;
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i) != null) {
                e = events.get(i);
                if (!e.isCanceled()) {
                    e.update();
                }
            }
        }
    }

    public void removeEvent(ID id) {
        events.set(id.getID(), null);
    }

    public void removeEvent(IEvent e) {
        events.set(e.getID().getID(), null);
    }

    public IEvent getEvent(ID id) throws EventException {
        if (events.get(id.getID()) != null) {
            return events.get(id.getID());
        } else {
            throw new EventException("No Event Found for this ID.");
        }
    }

    public IEvent getEvent(IEvent e) throws EventException {
        if (events.get(e.getID().getID()) != null) {
            return events.get(e.getID().getID());
        } else {
            throw new EventException("No Event Found for this ID.");
        }
    }

    /**
     *
     * @return Amount of Events in Event List
     */
    public int getEventsAmount() {
        int a = 0;
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i) != null) {
                a++;
            }
        }
        return a;
    }

    /**
     *
     * @return Free Size of Event List
     */
    public int getEventsFreeAmount() {
        return events.size() - getEventsAmount();
    }

    /**
     *
     * @return Size of Event List
     */
    public int getEventsSizeAmount() {
        return events.size();
    }
}
