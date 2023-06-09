package model;

import java.util.ArrayList;
import java.util.List;

// A muscle group with a list of exercises that work out that muscle
public class MuscleGroup {
    private String name;
    private List<Exercise> exercises;

    //EFFECTS: creates a muscle group with name and a empty list of exercises
    public MuscleGroup(String name) {
        this.name = name;
        this.exercises = new ArrayList<Exercise>();
    }

    //MODIFIES: this
    //EFFECTS: populates muscle groups with exercise
    public void populate(List<Exercise> exercises) {

        for (Exercise exercise : exercises) {
            this.exercises.add(exercise);
        }

    }

    public List<Exercise> getExercises() {
        return this.exercises;
    }


    public String getName() {
        return name;
    }
}
