package model;

import java.util.ArrayList;
import java.util.List;

// A workout filled with exercises
public class Workout {

    private String workoutGoal;
    private List exercises;
    private static List<MuscleGroup> muscleGroups;


    //EFFECTS: initializes exercises with an empty list of exercises
    //         and a list of muscle groups to choose exercises from
    public Workout(String workoutGoal, List<MuscleGroup> muscleGroups) {
        this.workoutGoal = workoutGoal;
        exercises = new ArrayList<Exercise>();
        this.muscleGroups = muscleGroups;
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


    public List<MuscleGroup> getMuscleGroups() {
        return muscleGroups;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public String getWorkoutGoal() {
        return workoutGoal;
    }
}
