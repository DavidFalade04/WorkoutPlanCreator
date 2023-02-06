package model;

import java.util.List;

public class Workout {
    private String workoutGoal;
    private List exercises;


    public Workout(){

    }

    //EFFECTS: adds an exercise to list of exercises
    public void add(){

    }

    //EFFECTS: removes an exercise
    public void remove(){}

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
