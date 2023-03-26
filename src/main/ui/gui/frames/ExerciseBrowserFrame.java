package ui.gui.frames;

import model.Day;
import model.Exercise;
import ui.gui.WorkoutCreatorAppGui;
import ui.gui.tools.AppButton;
import ui.gui.tools.AppFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Frame for browsing exercises
public class ExerciseBrowserFrame extends AppFrame implements ActionListener {

    WorkoutCreatorAppGui app;
    Day day;

    JPanel body;

    JButton back;
    JButton chest;
    JButton leg;
    JButton shoulder;

    JComboBox comboBox;
    List<Exercise> exercises;

    //EFFECTS: creates frame
    public ExerciseBrowserFrame(JFrame caller, WorkoutCreatorAppGui app, Day day) {
        super("Exercise Browser");
        caller.dispose();
        this.app = app;
        this.day = day;
        browser();
    }

    //MODIFIES: this
    //EFFECTS: initializes components of frame
    private void browser() {
        back = new AppButton("back");
        back.addActionListener(this);
        chest = new AppButton("chest");
        chest.addActionListener(this);
        leg = new AppButton("leg");
        leg.addActionListener(this);
        shoulder = new AppButton("shoulder");
        shoulder.addActionListener(this);
        body = new JPanel();
        body.setLayout(new GridLayout(2,2));
        body.add(back);
        body.add(chest);
        body.add(leg);
        body.add(shoulder);
        this.add(body);
    }


    @Override
    //MODIFIES: this
    //EFFECTS: listens to user input
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chest) {
            selector(0);
        }
        if (e.getSource() == back) {
            selector(1);
        }
        if (e.getSource() == leg) {
            selector(2);
        }
        if (e.getSource() == shoulder) {
            selector(3);
        }
        if (e.getSource() == comboBox) {
            Exercise exercise = exercises.get(comboBox.getSelectedIndex());
            new ExerciseEditorFrame(this, app, day, exercise);
        }
    }

    private void selector(int index) {
        exercises = app.getMuscleGroups().get(index).getExercises();
        JPanel selectPanel = new JPanel();

        List<String> exerciseNames = new ArrayList<>();

        for (Exercise e: exercises) {
            exerciseNames.add(e.getName());
        }

        comboBox = new JComboBox<>(exerciseNames.toArray());
        comboBox.addActionListener(this);


        selectPanel.add(comboBox);
        body.removeAll();
        body.add(selectPanel);
        body.updateUI();

    }
}
