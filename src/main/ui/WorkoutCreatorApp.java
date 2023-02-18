package ui;

import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// user interface of workout creator app
public class WorkoutCreatorApp {
    private List<WorkoutPlan> plans;
    private List<MuscleGroup> muscleGroups;
    private List<String> dayNames;
    private List<Day> defaultDays;
    private Scanner input;



    //EFFECTS: runs app and initializes empty lists
    public WorkoutCreatorApp() throws FileNotFoundException {
        plans = new ArrayList<>();
        muscleGroups = new ArrayList<>();
        defaultDays = new ArrayList<>();

        runApp();
    }

    //NOTE: pieces of code gotten from tellerApp
    //MODIFIES: this
    //EFFECTS:
    private void runApp() throws FileNotFoundException {

        boolean keepGoing = true;
        String command = null;

        init();

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
            System.out.println("\nview plan, edit plan or go back to menu?");
            System.out.println("\tv -> view");
            System.out.println("\tb -> back to menu");

            command = input.next();
            command = command.toLowerCase();

            if (command.equals("v")) {
                viewPlan(newPlan);
            } else if (command.equals("b")) {
                displayMainMenu();
            }

        }
    }

    //REQUIRES: valid day entered
    //EFFECTS: toggles a day in a workout
    private void dayViewer(Day day) {
        System.out.println("Day: " + day.getName());
        System.out.println("Status: " + day.getStatus());
        Workout workout = day.getWorkout();

        System.out.print("Workout Goal: ");

        if (workout == null) {
            System.out.println("none");

            System.out.println("c -> change status");
            System.out.println("s -> set workout");
            System.out.println("b -> back");
        } else {
            System.out.println(workout.getWorkoutGoal());

            System.out.println("c -> change status");
            System.out.println("e -> edit workout");
            System.out.println("b -> back");
        }
        String command = input.next();
        dayOptions(day, command);
    }

    //MODIFIES: this
    //EFFECTS: acts out specified user action on day
    private void dayOptions(Day day,String command) {
        if (command.equals("c")) {
            System.out.println("new status:");
            String status = input.next();
            day.setStatus(status);
        } else if (command.equals("s")) {
            System.out.println("new workout name: ");
            String name = input.next();

            Workout workout = new Workout();
            day.setWorkout(workout);
        }
    }

    //EFFECTS: displays workoutPlan contents
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
        System.out.println("provide a name for you new plan");
        String name = input.next();
        WorkoutPlan workoutplan = new WorkoutPlan(name, defaultDays);
        plans.add(workoutplan);
        System.out.println("workout plan: " + name + " created");
        return workoutplan;

    }

    //MODIFIES: this
    //EFFECTS: initializes workout creator app
    private void init() throws FileNotFoundException {
        initMuscleGroups();
        initDefaultDays();
        //NOTE: extracted from teller app
        input = new Scanner(System.in);
        input.useDelimiter("\n");

    }

    //
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

        for (String dayName : dayNames) {
            Day day = new Day(dayName);
            defaultDays.add(day);
        }

    }

    //MODIFIES: this
    //EFFECTS: Initializes muscle groups
    private void initMuscleGroups() throws FileNotFoundException {
        MuscleGroup chest = new MuscleGroup("chest");
        MuscleGroup back = new MuscleGroup("back");
        MuscleGroup legs = new MuscleGroup("legs");
        MuscleGroup shoulders = new MuscleGroup("shoulders");

        setMuscleGroups(chest, "data/ChestExercises.csv");
        setMuscleGroups(back, "data/BackExercises.csv");
        setMuscleGroups(legs,"data/LegExercises.csv");
        setMuscleGroups(shoulders, "data/ShoulderExercises.csv");

        muscleGroups.add(chest);
        muscleGroups.add(back);
        muscleGroups.add(legs);
        muscleGroups.add(shoulders);

    }

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
