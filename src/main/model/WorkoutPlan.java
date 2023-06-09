package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.ArrayList;
import java.util.List;

// A weekly workout plan that outlines what day workouts are on, are what days are rest days
public class WorkoutPlan implements Writable {

    private String name;
    private List<Day> days;


    //EFFECTS: names the workout plan, and sets the days of the weeks
    public WorkoutPlan(String name, List<Day> days) {
        this.name = name;
        this.days = new ArrayList<>();
        this.days.addAll(days);
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


    //EFFECTS: gets specific day from days
    //         if day is not valid returns null
    public Day getDay(String dayName) {
        for (Day day : days) {
            if (day.getName().equals(dayName)) {
                return day;
            }

        }
        return null;
    }

    public List<Day> getDays() {
        return days;
    }

    public String getName() {
        return name;
    }

    @Override
    //EFFECTS: converts WorkoutPlan to JsonObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("days", daysToJsonArray());

        return json;
    }

    //EFFECTS: converts the days into a JsonArray
    private JSONArray daysToJsonArray() {
        JSONArray jsonArray = new JSONArray();

        for (Day d : days) {
            jsonArray.put(d.toJson());
        }

        return jsonArray;
    }
}
