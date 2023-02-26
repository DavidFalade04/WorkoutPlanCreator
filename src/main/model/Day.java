package model;

// A Day in a weekly workout plan
public class Day {
    private final String name;
    private String status;
    private Workout workout;



    //EFFECTS: creates a day with a name with default status rest
    public Day(String name) {
        this.name = name;
        this.status = "rest";
    }

    //MODIFIES: this
    //EFFECTS: sets the day's workout
    public void setWorkout(Workout workout) {
        this.workout = workout;
    }


    //MODIFIES: this
    //EFFECTS: removes workout
    public void removeWorkout() {
        this.workout = null;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public Workout getWorkout() {

        return workout;
    }
}
