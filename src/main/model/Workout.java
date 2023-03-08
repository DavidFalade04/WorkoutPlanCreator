package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.ArrayList;
import java.util.List;

// A workout that can be filled with exercises
public class Workout implements Writable {

    private String workoutGoal;
    private List<Exercise> exercises;


    //EFFECTS: initializes exercises with an empty list of exercises
    //         and a list of muscle groups to choose exercises from
    public Workout(String workoutGoal) {
        this.workoutGoal = workoutGoal;
        exercises = new ArrayList<Exercise>();
    }

    //REQUIRES: exercise is not already added to workout
    //MODIFIES: this
    //EFFECTS: adds an exercise to list of exercises
    public void add(Exercise exercise) {
        this.exercises.add(exercise);
    }

    //MODIFIES: this
    //EFFECTS: change workout goal
    public void changeGoal(String goal) {
        this.workoutGoal = goal;
    }

    //REQUIRES: exercise is in workout
    //MODIFIES: this
    //EFFECTS: removes an exercise
    public void remove(Exercise exercise) {
        this.exercises.remove(exercise);
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public String getWorkoutGoal() {
        return workoutGoal;
    }

    @Override
    //EFFECTS: converts Workout to JsonObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("workout goal", workoutGoal);
        json.put("exercises", exercisesToJsonArray());
        return json;
    }

    //EFFECTS: converts List<Exercises> exercises to JsonArray
    private JSONArray exercisesToJsonArray() {
        JSONArray jsonArray = new JSONArray();
        for (Exercise e : exercises) {
            jsonArray.put(e.toJson());
        }
        return jsonArray;
    }
}
