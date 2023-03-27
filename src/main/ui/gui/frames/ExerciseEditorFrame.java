package ui.gui.frames;

import model.Day;
import model.Exercise;
import ui.gui.WorkoutCreatorAppGui;
import ui.gui.tools.AppFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.Kernel;

// Frame for editing exercises
public class ExerciseEditorFrame extends AppFrame implements ActionListener {
    WorkoutCreatorAppGui app;
    Day day;
    Exercise exercise;
    JTextField textField;
    JPanel body;
    JButton button;
    JTextField reps;
    JTextField sets;


    //MODIFIES: this
    //EFFECTS: creates frame
    public ExerciseEditorFrame(JFrame caller, WorkoutCreatorAppGui app, Day day, Exercise exercise) {
        super("Exercise editor", caller);
        this.app = app;
        this.day = day;
        this.exercise = exercise;
        body = new JPanel();
        editor();
        this.add(body);
    }

    //MODIFIES: this
    //EFFECTS: creates text-fields and buttons
    private void editor() {
        sets = new JTextField("# of sets");
        reps = new JTextField("# of reps");
        button = new JButton("submit");
        button.addActionListener(this);
        body.add(sets);
        body.add(reps);
        body.add(button);
    }

    @Override
    //MODIFIES: this
    //EFFECTS: listens to user input
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            exercise.setSets(Integer.parseInt(sets.getText()));
            exercise.setReps(Integer.parseInt(reps.getText()));
            day.getWorkout().remove(exercise);
            day.getWorkout().add(exercise);
            new WorkoutWorkshopFrame(this, app, day);

        }
    }
}
