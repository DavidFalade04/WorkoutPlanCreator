package ui.gui.frames;

import model.Day;
import model.Workout;
import ui.gui.WorkoutCreatorAppGui;
import ui.gui.tools.AppButton;
import ui.gui.tools.AppFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Frame for workout editing
public class WorkoutWorkshopFrame extends AppFrame implements ActionListener {
    WorkoutCreatorAppGui app;
    Day day;
    Workout workout;

    JButton changeName;
    JButton addExercise;
    JButton removeExercise;
    JButton editExercise;


    JPanel body;
    JPanel options;

    JPanel changeNamePanel;
    JButton submit;
    JTextField textField;


    //MODIFIES: this
    //EFFECTS: creates frame and loads workout from day
    public WorkoutWorkshopFrame(JFrame caller, WorkoutCreatorAppGui app, Day day) {
        super("Workout Workshop");
        caller.dispose();
        this.workout = day.getWorkout();
        init(app, day);
    }

    //MODIFIES: this, day
    //EFFECTS: creates frame and creates a new workout adding it to day
    public WorkoutWorkshopFrame(JFrame caller, WorkoutCreatorAppGui app, Day day, String name) {
        super("Workout Workshop");
        caller.dispose();
        this.workout = new Workout(name);
        day.setWorkout(workout);
        init(app, day);

    }

    //MODIFIES: this, day
    //EFFECTS: initializes frame
    private void init(WorkoutCreatorAppGui app, Day day) {
        this.app = app;
        this.day = day;
        body = new JPanel();
        updateStats();
        this.add(body);
        initOptions();
        initChangeName();
        update(options);
    }

    //MODIFIES: this
    //EFFECTS: update statistics
    private void updateStats() {
        JPanel statsPanel = new JPanel();
        JLabel stats = new JLabel("Workout Goal:" + this.day.getWorkout().getWorkoutGoal());
        statsPanel.add(stats);
        body.add(statsPanel);
    }


    //MODIFIES: this
    //EFFECTS: initializes changeNamePanel
    private void initChangeName() {
        changeNamePanel = new JPanel();
        textField = new JTextField("new Workout name");
        submit = new JButton("submit");
        submit.addActionListener(this);
        changeNamePanel.add(textField);
        changeNamePanel.add(submit);
    }

    //MODIFIES: this
    //EFFECTS: initialise optionsPanel
    private void initOptions() {
        changeName = new AppButton("change name");
        addExercise = new AppButton("add exercise");
        removeExercise = new AppButton("remove an exercise");
        editExercise = new AppButton("edit an exercise");

        changeName.addActionListener(this);
        addExercise.addActionListener(this);
        removeExercise.addActionListener(this);
        editExercise.addActionListener(this);

        options = new JPanel();
        options.setLayout(new GridLayout(3,1));

        options.add(changeName);
        options.add(addExercise);
        options.add(removeExercise);
        options.add(editExercise);
    }


    @Override
    //MODIFIES: this
    //EFFECTS: read user input
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == changeName) {
            update(changeNamePanel);
        }
        if (e.getSource() == submit) {
            day.getWorkout().changeGoal(textField.getText());
            update(options);
        }
        if (e.getSource() == addExercise) {
            new ExerciseBrowserFrame(this, app, day);
        }
        if (e.getSource() == removeExercise) {
            new RemoveExerciseFrame(this, app, day);
        }
        if (e.getSource() == editExercise) {
            new EditExerciseFrame(this, app, day);
        }
    }

    //MODIFIES: this
    //EFFECTS: update state of program
    private void update(JPanel panel) {
        body.removeAll();
        updateStats();
        body.add(panel);
        body.updateUI();
    }
}
