package model;

import java.util.List;

public class Day {
    private final String name;
    private String status;
    private Workout workout;



    //EFFECTS: creates a day with a name with default status rest
    public Day(String name) {
        this.name = name;
    }

    //EFFECTS: adds a workout
    public void add(){}

    //EFFECTS: removes workout
    public void remove(){}


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
