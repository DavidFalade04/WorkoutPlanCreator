package ui;

import model.Exercise;
import model.MuscleGroup;
import model.WorkoutPlan;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// user interface of workout creator app
public class WorkoutCreatorApp {
    private List<WorkoutPlan> plans;
    private List<MuscleGroup> muscleGroups;
    private Scanner input;



    public WorkoutCreatorApp() throws FileNotFoundException {
        runApp();
    }

    //NOTE: pieces of code gotten from tellerApp
    //MODIFIES: this
    //EFFECTS:
    private void runApp() throws FileNotFoundException {

        boolean keepGoing = true;
        String command = null;

        init();

/*
        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");

*/
    }

    //MODIFIES: this
    //EFFECTS: Initializes muscle groups and exercises and days
    private void init() throws FileNotFoundException {
        MuscleGroup chest = new MuscleGroup("chest");
        MuscleGroup back = new MuscleGroup("back");
        MuscleGroup legs = new MuscleGroup("legs");
        MuscleGroup shoulders = new MuscleGroup("shoulders");

        setMuscleGroups(chest, "data/ChestExercises.csv");
        setMuscleGroups(chest, "data/BackExercises.csv");
        setMuscleGroups(legs,"data/LegExercises.csv");
        setMuscleGroups(shoulders, "data/ShoulderExercises.csv");

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
            System.out.println("added " + name);
        }
        muscleGroup.populate(exercises);
    }

    //EFFECTS: display main menu
    private void displayMenu() {}



}
