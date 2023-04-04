package model;

import java.util.Calendar;
import java.util.Date;



// an event in the program with the time it happened and a description
public class Event {
    private static final int HASH_CONSTANT = 13;
    private Date dateLogged;
    private String description;

//EFFECTS: constructs an event with the date it was logged and a description
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    public Date getDate() {
        return dateLogged;
    }

    public String getDescription() {
        return description;
    }

    @Override
    //EFFECTS: determines if an object is equal to this
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }

        Event otherEvent = (Event) other;

        return (this.dateLogged.equals(otherEvent.dateLogged)
                && this.description.equals(otherEvent.description));
    }

    @Override
    //EFFECTS: returns hashcode
    public int hashCode() {
        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());
    }

    @Override
    //EFFECTS: returns date logged and description
    public String toString() {
        return dateLogged.toString() + "\n" + description;
    }


}
