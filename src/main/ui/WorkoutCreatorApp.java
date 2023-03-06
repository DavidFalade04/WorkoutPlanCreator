package ui;

import model.*;
import persistance.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// user interface of workout creator app
public class WorkoutCreatorApp {
    private List<WorkoutPlan> plans;
    private List<MuscleGroup> muscleGroups;
    private List<String> dayNames;
    private List<Day> defaultDays;
    private List<Workout> workoutTemplates;
    private Scanner input;
    private JsonReader jsonReader;



    //MODIFIES: this, MuscleGroup, Exercise, Workout
    //EFFECTS: runs app and initializes empty lists
    public WorkoutCreatorApp() throws IOException {
        plans = new ArrayList<>();
        muscleGroups = new ArrayList<>();
        defaultDays = new ArrayList<>();
        jsonReader = new JsonReader("data/JsonData/TestWorkoutPlan.json");


        runApp();
    }

    //MODIFIES: this
    //EFFECTS: loads saved data from file
    private void load() throws IOException {
        List<WorkoutPlan> loadedPlans = jsonReader.read();
        for (WorkoutPlan wp : loadedPlans) {
            plans.add(wp);
            System.out.println("loaded " + wp.getName() + "from memory");
        }



    }

    //NOTE: pieces of code gotten from tellerApp
    //MODIFIES: this, MuscleGroup, Exercise
    //EFFECTS: Runs Workout Creator App
    private void runApp() throws IOException {

        boolean keepGoing = true;
        String command = null;

        init();
        load();

        //NOTE: extracted from teller app
        while (keepGoing) {
            displayMainMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");


    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            WorkoutPlan newPlan = createWorkoutPlan();
            System.out.println("view plan or go back to menu?");
            System.out.println("\tv -> view");
            System.out.println("\tb -> back to menu");

            command = input.next();
            command = command.toLowerCase();

            if (command.equals("v")) {
                viewPlan(newPlan);
            } else if (command.equals("b")) {
                displayMainMenu();
            }

        } else if (command.equals("l")) {
            loadPlans();
        }
    }

    //EFFECTS: loads created plans
    private void loadPlans() {
        if (plans.isEmpty()) {
            System.out.println("No plans created yet");
            System.out.println("going back to menu...");
            return;
        }
        System.out.println("\nChoose a saved plan:");
        int index = 0;
        for (WorkoutPlan workoutPlan : plans) {
            System.out.println("\t" + index + ": " + workoutPlan.getName());
            index++;
        }
        System.out.print("enter slot number: ");
        index = input.nextInt();
        if (index < plans.size()) {
            viewPlan(plans.get(index));
        } else {
            loadPlans();
        }

    }

    //REQUIRES: day's name must be "monday", "tuesday", "wednesday",
    //          "thursday", "friday", "saturday", or "sunday"
    //MODIFIES: this, Workout, Exercise
    //EFFECTS: toggles a day in a workout
    private void dayViewer(Day day) {
        System.out.println("Day: " + day.getName());
        System.out.println("Status: " + day.getStatus());
        Workout workout = day.getWorkout();

        System.out.print("Workout Goal: ");

        if (workout == null) {
            System.out.println("none");


            System.out.println("s -> set workout");
            System.out.println("v -> view exercises");
            System.out.println("b -> back");
        } else {
            System.out.println(workout.getWorkoutGoal());

            System.out.println("e -> edit workout");
            System.out.println("v -> view exercises");
            System.out.println("b -> back");
        }
        String command = input.next();
        dayOptions(day, command);
    }

    //REQUIRES: if command is e, day must already have workout set
    //MODIFIES: this, Workout, Exercise
    //EFFECTS: acts out specified user action on day
    private void dayOptions(Day day,String command) {
        Workout workout = day.getWorkout();

        if (command.equals("s")) {
            System.out.println("m -> make a new workout from scratch");
            System.out.println("b -> browse our workout presets");
            String choice = input.next();
            if (choice.equals("m")) {
                newWorkout(day);
            } else if (choice.equals("b")) {
                browseWorkoutPresets(day);
            } else {
                dayOptions(day, command);
            }
        } else if (command.contains("e")) {
            editWorkout(workout, day);
        } else if (command.equals("v")) {
            displayExercises(workout);
        } else if (command.equals("b")) {
            return;
        }

    }

    //EFFECTS: queries user for options after viewing exercises
    private void viewWorkoutQuery(Workout workout) {

        System.out.print("enter exercise number to edit: ");
        int index = input.nextInt();
        Exercise e = workout.getExercises().get(index);
        editExercise(e);

    }

    //MODIFIES: this, Workout, Exercise
    //EFFECTS: displays workout presets to browse
    private void browseWorkoutPresets(Day day) {
        int index = 0;
        System.out.println("\nWorkout Presets:");
        for (Workout w: workoutTemplates) {
            System.out.println("\t(" + index + ") " + w.getWorkoutGoal());
            index++;
        }
        System.out.print("enter a preset number to view: ");
        index = input.nextInt();
        if (index < workoutTemplates.size()) {
            viewWorkoutPreset(day, workoutTemplates.get(index));
        } else {
            browseWorkoutPresets(day);
        }
    }

    //MODIFIES: this, Workout, Exercise
    //EFFECTS: views the exercises of a preset
    private void viewWorkoutPreset(Day day, Workout preset) {
        displayExercises(preset);
        System.out.println("s -> select this preset");
        System.out.println("b -> back to other presets");
        String choice = input.next();
        if (choice.equals("s")) {
            loadWorkoutPreset(day, preset);
            System.out.println("workout: " + preset.getWorkoutGoal() + " selected!");
            dayViewer(day);
        } else if (choice.equals("b")) {
            browseWorkoutPresets(day);
        } else {
            viewWorkoutPreset(day, preset);
        }
    }

    //MODIFIES: Workout, Exercise
    //EFFECTS: creates a new workout from a preset
    private void loadWorkoutPreset(Day day, Workout preset) {
        newWorkout(day, preset.getWorkoutGoal());
        Workout workout = day.getWorkout();
        for (Exercise e : preset.getExercises()) {
            Exercise newExercise = new Exercise(e.getName());
            newExercise.setSets(e.getSets());
            newExercise.setReps(e.getReps());
            workout.add(newExercise);
        }

    }

    //MODIFIES: Workout, Exercise
    //EFFECTS: creates a new workout from user input and then brings you to edit menu
    private void newWorkout(Day day) {
        Workout workout;
        System.out.print("new workout name: ");
        String name = input.next();
        newWorkout(day, name);
        editWorkout(day.getWorkout(), day);
    }

    //MODIFIES: this
    //EFFECTS: creates a new workout
    private void newWorkout(Day day, String name) {
        Workout workout = new Workout(name);
        day.setWorkout(workout);
        day.setStatus("doing " + day.getWorkout().getWorkoutGoal());
    }

    //EFFECTS: displays exercises in a workout and returns false if no exercise
    //         and true otherwise
    private Boolean displayExercises(Workout workout) {
        if (workout == null) {
            System.out.println("no exercises :(");
            return false;
        }
        List<Exercise> exercises = workout.getExercises();
        int index = 0;
        for (Exercise e : exercises) {
            System.out.print("(" + index + ") ");
            displayExercise(e);
            index++;
        }
        return true;

    }

    //EFFECTS: displays the properties of a exercise
    private void displayExercise(Exercise e) {
        System.out.println(e.getName());
        System.out.println("\t sets: " + e.getSets());
        System.out.println("\t reps: " + e.getReps());
        System.out.println("\t pr: " + e.getPr());
    }

    //REQUIRES: workout should not be null
    //MODIFIES: this, Workout, Exercise
    //EFFECTS: edits workouts
    private void editWorkout(Workout workout, Day day) {
        System.out.println("c -> change goal");
        System.out.println("a -> add an exercise");
        System.out.println("r -> remove an exercises");
        System.out.println("e -> edit an exercise");
        System.out.println("b -> back");
        String command = input.next();

        if (command.equals("c")) {
            String goal = input.next();
            workout.changeGoal(goal);
        } else if (command.equals("a")) {
            addExercise(workout, day);
        } else if (command.equals("r")) {
            removeExercise(workout, day);
        } else if (command.equals("e")) {
            if (displayExercises(workout)) {
                viewWorkoutQuery(workout);
            }
        } else if (command.equals("b")) {
            dayViewer(day);
        }
    }

    //MODIFIES: Workout, Exercise,
    //EFFECTS: removes an exercise from a workout
    private void removeExercise(Workout workout, Day day) {
        List<Exercise> exercises = workout.getExercises();
        if (exercises.isEmpty() | exercises == null) {
            System.out.println("You have no exercises in this workout");
            System.out.println("going back to menu...");
            editWorkout(workout, day);
        }
        displayExercises(workout);
        System.out.print("enter the number of the exercise you would like to remove: ");
        int index = input.nextInt();
        if (index < exercises.size()) {
            workout.getExercises().remove(index);
        } else {
            removeExercise(workout, day);
        }
        editWorkout(workout, day);
    }

    //MODIFIES: this, Exercise, Workout
    //EFFECTS: adds an exercise to a workout
    private void addExercise(Workout workout, Day day) {
        Exercise exercise = browse();
        editExercise(exercise);
        workout.add(exercise);
        editWorkout(workout, day);
    }

    //MODIFIES: Exercise
    //EFFECTS: edits the properties of an exercise, through user input
    private void editExercise(Exercise exercise) {
        displayExercise(exercise);

        System.out.print("\n# of sets: ");
        int sets = input.nextInt();
        System.out.print("# of reps: ");
        int reps = input.nextInt();
        editExercise(exercise, sets, reps);
    }

    //MODIFIES: Exercise
    //EFFECTS: edits the properties of an exercise
    private void editExercise(Exercise exercise, int sets, int reps) {

        exercise.setSets(sets);
        exercise.setReps(reps);

    }

    //EFFECTS: allows user to browse exercises sorted in muscle groups
    private Exercise browse() {
        String command = chooseMuscleGroup();
        List<Exercise> exercises = null;
        if (command.equals("c")) {
            exercises = muscleGroups.get(0).getExercises();
        } else if (command.equals("b")) {
            exercises = muscleGroups.get(1).getExercises();
        } else if (command.equals("l")) {
            exercises = muscleGroups.get(2).getExercises();
        } else if (command.equals("s")) {
            exercises = muscleGroups.get(3).getExercises();
        } else {
            browse();
        }

        Exercise exercise = chooseExercise(exercises);
        return exercise;
    }

    //EFFECTS: allows user to view and choose exercises in a list
    private Exercise chooseExercise(List<Exercise> exercises) {
        int index = 0;
        Exercise exercise = null;
        for (Exercise e : exercises) {
            System.out.println(index + ": " + e.getName());
            index++;
        }
        System.out.print("enter the number of the exercise you would like to select: ");
        index = input.nextInt();

        if (index < exercises.size()) {
            exercise = exercises.get(index);
        } else {
            chooseExercise(exercises);
        }

        return exercise;
    }

    //EFFECTS: queries user for a muscle group
    private String chooseMuscleGroup() {
        System.out.println("\nchoose a muscle group:");
        System.out.println("\tb -> back");
        System.out.println("\tc -> chest");
        System.out.println("\tl -> leg");
        System.out.println("\ts -> shoulder");
        String command = input.next();
        return command;
    }

    //MODIFIES: this, Exercise, Workout
    //EFFECTS: allows user to select view a plan and select a day
    private void viewPlan(WorkoutPlan workoutPlan) {
        System.out.println("viewing " + workoutPlan.getName());
        System.out.println("");
        listDays(workoutPlan);
        System.out.println("b -> back");
        System.out.print("enter day name to view: ");
        String command = input.next();
        command.toLowerCase();
        if (command.equals("b")) {
            displayMainMenu();
        } else {
            Day day = workoutPlan.getDay(command);
            if (day == null) {
                viewPlan(workoutPlan);
            } else {
                dayViewer(day);
            }
        }
    }

    //REQUIRES: workout plan days length to be = 7
    //EFFECTS: list the days of a workout plan and its status
    private void listDays(WorkoutPlan workoutPlan) {
        System.out.println("Day:" + "         " + "Status:");
        for (String dayNames: dayNames) {
            Day day = workoutPlan.getDay(dayNames);
            int space = 12 - day.getName().length();
            System.out.print(day.getName());
            for (int i = 0; i <= space; i++) {
                System.out.print(" ");
            }
            System.out.println(day.getStatus());
        }
    }


    //MODIFIES: this
    //EFFECTS: creates a workout plan and saves it to plans
    private WorkoutPlan createWorkoutPlan() {
        System.out.print("New Workout plan name: ");
        String name = input.next();
        initDefaultDays();
        WorkoutPlan workoutplan = new WorkoutPlan(name, defaultDays);
        plans.add(workoutplan);
        System.out.println("workout plan: " + name + " created");
        return workoutplan;

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

    //Modifies: this
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

    //EFFECTS: display main menu
    private void displayMainMenu() {
        System.out.println("\nWelcome to Workout Plan Creator!");
        System.out.println("\tc -> create new workout plan");
        System.out.println("\tl -> load saved workout plans");
        System.out.println("\tq -> quit app");

    }



}
