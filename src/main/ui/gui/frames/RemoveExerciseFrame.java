package ui.gui.frames;

import model.Day;
import model.Exercise;
import ui.gui.WorkoutCreatorAppGui;
import ui.gui.tools.AppFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Frame for removing exercise
public class RemoveExerciseFrame  extends AppFrame implements ActionListener {

    WorkoutCreatorAppGui app;
    Day day;
    JPanel body;
    JComboBox comboBox;
    List<Exercise> exercises;

    //MODIFIES: this
    // EFFECTS: creates frame
    public RemoveExerciseFrame(JFrame callee, WorkoutCreatorAppGui app, Day day) {
        super("Remove Exercise");
        callee.dispose();
        this.app = app;
        this.day = day;
        body = new JPanel();
        removeExercise();
    }

    //MODIFIES: this
    //EFFECTS: initializes frame
    private void removeExercise() {
        exercises = day.getWorkout().getExercises();
        if (exercises.isEmpty()) {
            JOptionPane.showMessageDialog(this, "no exercises added :(");
            new WorkoutWorkshopFrame(this, app, day);
        } else {
            List<String> exerciseNames = new ArrayList<>();
            for (Exercise e: exercises) {
                exerciseNames.add(e.getName());
            }
            comboBox = new JComboBox(exerciseNames.toArray());
            comboBox.addActionListener(this);
            comboBox.setPreferredSize(new Dimension(100,30));
            JPanel body = new JPanel();
            body.add(comboBox);
            this.add(body);
        }
    }

    @Override
    //EFFECTS: listens to user input
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBox) {
            Exercise exercise = exercises.get(comboBox.getSelectedIndex());
            day.getWorkout().remove(exercise);
            new WorkoutWorkshopFrame(this, app, day);
        }
    }
}
