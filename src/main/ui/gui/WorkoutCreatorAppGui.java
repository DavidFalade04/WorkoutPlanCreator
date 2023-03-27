package ui.gui;

import model.*;
import persistance.JsonReader;
import persistance.JsonWriter;
import ui.gui.frames.IndexFrame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// user interface of workout creator app
public class WorkoutCreatorAppGui {

    private List<WorkoutPlan> plans;
    private List<MuscleGroup> muscleGroups;
    private List<String> dayNames;
    private List<Day> defaultDays;
    private List<Workout> workoutTemplates;
    private Scanner input;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private Boolean loadedFromFile;

    private WorkoutPlan currentWorkoutPlan;


    //EFFECTS: initializes data and starts app
    public WorkoutCreatorAppGui() throws FileNotFoundException {
        plans = new ArrayList<>();
        muscleGroups = new ArrayList<>();
        defaultDays = new ArrayList<>();
        jsonReader = new JsonReader("data/JsonData/WorkoutAppData.json");
        loadedFromFile = false;
        init();
        IndexFrame index = new IndexFrame(this, null);
    }

    //MODIFIES: this
    //EFFECTS: creates a workout plan
    public WorkoutPlan createWorkoutPlan(String name) {
        initDefaultDays();
        WorkoutPlan workoutplan = new WorkoutPlan(name, defaultDays);
        plans.add(workoutplan);
        this.currentWorkoutPlan = workoutplan;
        return workoutplan;
    }

    //MODIFIES: this
    //EFFECTS: creates days of the week and initializes names of day
    private void initDefaultDays() {
        dayNames = new ArrayList<>();
        dayNames.add("monday");
        dayNames.add("tuesday");
        dayNames.add("wednesday");
        dayNames.add("thursday");
        dayNames.add("friday");
        dayNames.add("saturday");
        dayNames.add("sunday");

        defaultDays = new ArrayList<>();
        for (String dayName : dayNames) {
            Day day = new Day(dayName);
            defaultDays.add(day);
        }

    }



    //MODIFIES: this, MuscleGroup, Exercise, Workout
    //EFFECTS: initializes workout creator app
    private void init() throws FileNotFoundException {
        initMuscleGroups();
        initWorkoutTemplates();
        initDefaultDays();
        //NOTE: extracted from teller app
        input = new Scanner(System.in);
        input.useDelimiter("\n");

    }




    //MODIFIES: this, Workout, Exercise
    //EFFECTS: initializes pre-made workout templates
    private void initWorkoutTemplates() {
        Workout backBreaker = new Workout("Back Breaker");
        Workout legDay = new Workout("Leg Day!!");
        Workout pecPopper = new Workout("Pec Popper");
        Workout shoulderSlammer = new Workout("Shoulder Slammer");

        setWorkout(backBreaker, "data/WorkoutTemplateExercises/BackBreaker.csv");
        setWorkout(legDay, "data/WorkoutTemplateExercises/LegDay.csv");
        setWorkout(pecPopper, "data/WorkoutTemplateExercises/PecPopper.csv");
        setWorkout(shoulderSlammer,"data/WorkoutTemplateExercises/Shoulder Slammer.csv");

        workoutTemplates = new ArrayList<Workout>();
        workoutTemplates.add(backBreaker);
        workoutTemplates.add(legDay);
        workoutTemplates.add(pecPopper);
        workoutTemplates.add(shoulderSlammer);

    }

    //REQUIRES: path must csv, file in order of name, sets, reps
    //MODIFIES: Workout, Exercise
    //EFFECTS: populates a workout with exercises
    private void setWorkout(Workout workout, String path) {
        String name;
        File file = new File(path);
        Scanner input = null;
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        input.useDelimiter(",");

        while (input.hasNext()) {
            name = input.next();
            Exercise exercise = new Exercise(name);
            workout.add(exercise);
            int sets = input.nextInt();
            int reps = input.nextInt();
            editExercise(exercise, sets, reps);
        }

    }

    //MODIFIES: Exercise
    //EFFECTS: edits the properties of an exercise
    private void editExercise(Exercise exercise, int sets, int reps) {

        exercise.setSets(sets);
        exercise.setReps(reps);

    }



    //MODIFIES: this, MuscleGroup
    //EFFECTS: Initializes muscle groups
    private void initMuscleGroups() throws FileNotFoundException {
        MuscleGroup chest = new MuscleGroup("chest");
        MuscleGroup back = new MuscleGroup("back");
        MuscleGroup legs = new MuscleGroup("legs");
        MuscleGroup shoulders = new MuscleGroup("shoulders");

        setMuscleGroups(chest, "data/MuscleGroupExercises/ChestExercises.csv");
        setMuscleGroups(back, "data/MuscleGroupExercises/BackExercises.csv");
        setMuscleGroups(legs,"data/MuscleGroupExercises/LegExercises.csv");
        setMuscleGroups(shoulders, "data/MuscleGroupExercises/ShoulderExercises.csv");

        muscleGroups.add(chest);
        muscleGroups.add(back);
        muscleGroups.add(legs);
        muscleGroups.add(shoulders);

    }

    //MODIFIES: MuscleGroup
    //EFFECTS: creates muscle groups and populates them with exercises
    private void setMuscleGroups(MuscleGroup muscleGroup, String path) throws FileNotFoundException {
        String name;
        List<Exercise> exercises = new ArrayList<Exercise>();
        File file = new File(path);
        Scanner input = new Scanner(file);

        input.useDelimiter(",");

        while (input.hasNext()) {
            name = input.next();
            exercises.add(new Exercise(name));
        }
        muscleGroup.populate(exercises);
    }

    //MODIFIES: WorkoutAppData.json
    //EFFECTS: saves all workout plans to file
    public void save() {
        if (loadedFromFile) {
            jsonWriter = new JsonWriter("data/JsonData/WorkoutAppData.json");
            try {
                jsonWriter.open();
                jsonWriter.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        for (WorkoutPlan plan : plans) {
            saveToFile(plan);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads workout plans from file
    public void load() {
        try {
            List<WorkoutPlan> loadedPlans = jsonReader.read();
            for (WorkoutPlan wp : loadedPlans) {
                if (!plans.contains(wp)) {
                    plans.add(wp);
                    System.out.println("loaded " + wp.getName() + " from memory");
                }
                loadedFromFile = true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //MODIFIES: WorkoutAppData.json
    //EFFECTS: saves workoutPlan to file
    private void saveToFile(WorkoutPlan wp) {

        jsonWriter = new JsonWriter("data/JsonData/WorkoutAppData.json");
        try {
            String file = JsonReader.readFile("data/JsonData/WorkoutAppData.json");
            jsonWriter.open();
            jsonWriter.write(wp, file);
            jsonWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<MuscleGroup> getMuscleGroups() {

        return this.muscleGroups;
    }

    public WorkoutPlan getCurrentWorkoutPlan() {
        return currentWorkoutPlan;
    }

    public void setCurrentWorkoutPlan(WorkoutPlan currentWorkoutPlan) {
        this.currentWorkoutPlan = currentWorkoutPlan;
    }


    public List<WorkoutPlan> getPlans() {
        return plans;
    }


}
