package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// Log of events that have occurred
public class EventLog implements Iterable<Event> {
    private static EventLog theLog;
    private Collection<Event> events;

    //EFFECTS: constructs event log
    private EventLog() {
        events = new ArrayList<Event>();
    }


    //MODIFIES: this
    //EFFECTS: Gets instance of EventLog - creates if it doesn't already exist
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }

        return theLog;
    }


    //MODIFIES: this
    //EFFECTS: adds an event to the eventLog
    public void logEvent(Event e) {
        events.add(e);
    }

    //MODIFIES: this
    //EFFECTS: Clears the event log and logs the event.
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    @Override
    //EFFECTS: returns an iterator
    public Iterator<Event> iterator() {
        return events.iterator();
    }

}
