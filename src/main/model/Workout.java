package model;

import java.util.ArrayList;
import java.util.List;

public class Workout {
    private String workoutGoal;
    private List exercises;


    //EFFECTS initializes exercises with an empty list
    public Workout(String workoutGoal) {
        this.workoutGoal = workoutGoal;
        exercises = new ArrayList<Exercise>();
    }

    //EFFECTS: adds an exercise to list of exercises
    public void add(Exercise exercise){

    }

    //EFFECTS: change workout goal
    public void changeGoal(String goal) {}

    //EFFECTS: removes an exercise
    public void remove(Exercise exercise){}

    //EFFECTS: browse a list of exercises to choose from
    private Exercise choose() {

        return null;
    }

    public List getExercises() {
        return exercises;
    }

    public String getWorkoutGoal() {
        return workoutGoal;
    }
}
