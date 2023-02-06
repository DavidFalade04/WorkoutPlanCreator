package model;

import java.util.List;

public class WorkoutPlan {

    private String name;
    private String type;
    private List days;


    //EFFECTS: names the workout plan, and sets the days of the weeks
    public WorkoutPlan(String name, List days){
        //TODO:
    }

    //MODIFIES: this
    //EFFECTS: changes name of workout plan
    public void rename(){}

    //MODIFIES: this
    //EFFECTS: reset days to being empty
    public void clear(){}

    //MODIFIES: this
    //EFFECTS: changes preset
    public void changePreset(){}

    //REQUIRES: day must be a valid day name
    //MODIFIES: day
    //EFFECTS: changes day
    public void edit(String day){}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
