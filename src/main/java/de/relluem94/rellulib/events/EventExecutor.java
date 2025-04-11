package de.relluem94.rellulib.events;

import de.relluem94.rellulib.FixedSizeList;
import de.relluem94.rellulib.ID;
import de.relluem94.rellulib.exceptions.EventException;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

public class EventExecutor implements IEventExecutor {

    private final FixedSizeList<IEvent> events;

    public EventExecutor() {
        events = new FixedSizeList<>(100);
    }

    public EventExecutor(int eventAmount) {
        events = new FixedSizeList<>(eventAmount);
    }

    public void registerEvent(IEvent e) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i) == null) {
                events.set(i, e);
                events.get(i).setID(i);
                return;
            }
        }
    }

    public void cleanUp() {
        Collections.fill(events, null);
    }

    /**
     * Put this in a loop in a thread
     *
     *
     */
    public int executeEvents() {
        int executed = 0;
        IEvent e;
        for (IEvent event : events) {
            if (event != null) {
                e = event;
                if (!e.isCanceled()) {
                    e.update();
                    executed++;
                }
            }
        }
        return executed;
    }

    public void removeEvent(@NotNull ID id) {
        events.set(id.getID(), null);
    }

    public void removeEvent(@NotNull IEvent e) {
        events.set(e.getID().getID(), null);
    }

    public IEvent getEvent(@NotNull ID id) throws EventException {
        if (events.get(id.getID()) != null) {
            return events.get(id.getID());
        } else {
            throw new EventException("No Event Found for this ID.");
        }
    }

    public IEvent getEvent(@NotNull IEvent e) throws EventException {
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
        for (IEvent event : events) {
            if (event != null) {
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
