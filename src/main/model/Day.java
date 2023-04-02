package model;

import org.json.JSONObject;
import persistance.Writable;

// A Day in a weekly workout plan
public class Day implements Writable {
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
        EventLog.getInstance().logEvent(new Event("new Workout added"));
    }


    //MODIFIES: this
    //EFFECTS: removes workout
    public void removeWorkout() {
        this.workout = null;
        EventLog.getInstance().logEvent(new Event("Workout removed"));
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

    @Override
    //EFFECTS: converts Day to JsonObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("status", status);
        if (workout != null) {
            json.put("workout", workout.toJson());
        } else {
            json.put("workout", new JSONObject());

        }

        return json;
    }
}
