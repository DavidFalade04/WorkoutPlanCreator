package ui.gui.frames;

import model.Workout;
import model.WorkoutPlan;
import ui.WorkoutCreatorApp;
import ui.gui.WorkoutCreatorAppGui;
import ui.gui.tools.AppFrame;
import ui.gui.tools.Title;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Frame for creating a workout plan
public class CreateFrame extends AppFrame implements ActionListener {

    JButton submit;
    JTextField textField;
    WorkoutCreatorAppGui app;

    //MODIFIES: this
    //EFFECTS: creates frame
    public CreateFrame(JFrame caller, WorkoutCreatorAppGui app) {
        super("WorkoutPlan Creator", caller);

        this.app = app;
        this.setTitle("Create");

        JPanel queryPanel = new JPanel();
        textField = new JTextField("Workout name");
        textField.setPreferredSize(new Dimension(100, 50));
        submit = new JButton("Create");
        queryPanel.add(textField);
        queryPanel.add(submit);
        submit.addActionListener(this);
        queryPanel.setBorder(new EmptyBorder(50, 0, 50, 0));

        this.add(queryPanel, BorderLayout.CENTER);


    }


    @Override
    //MODIFIES: this
    //EFFECTS: listens to user input
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String name = textField.getText();
            WorkoutPlan workoutPlan = app.createWorkoutPlan(name);
            new PlanViewerFrame(this, app, workoutPlan);
        }
    }
}
