package model;

import java.util.List;

// A weekly workout plan that outlines what day workouts are on, are what days are rest days
public class WorkoutPlan {

    private String name;
    private List<Day> days;


    //EFFECTS: names the workout plan, and sets the days of the weeks
    public WorkoutPlan(String name, List<Day> days) {
        this.name = name;
        this.days = days;
    }

    //MODIFIES: this
    //EFFECTS: changes name of workout plan
    public void rename(String name) {
        this.name = name;
    }

    //MODIFIES: this, Day
    //EFFECTS: reset days to being empty
    public void clear() {
        for (Day day : days) {
            day.removeWorkout();
        }
    }


    //REQUIRES: day must be a valid day name
    //EFFECTS: gets specific day from days
    public Day getDay(String dayName) {
        for (Day day : days) {
            if (dayName == day.getName()) {
                return day;
            }

        }
        return null;
    }


    public String getName() {
        return name;
    }

}
