package ui.gui.frames;

import model.Day;
import model.Exercise;
import ui.gui.WorkoutCreatorAppGui;
import ui.gui.tools.AppFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Frame for viewing day' exercises
public class WorkoutViewer extends AppFrame implements ActionListener {
    JPanel body;
    Day day;
    JButton back;
    WorkoutCreatorAppGui app;

    public WorkoutViewer(JFrame caller, WorkoutCreatorAppGui app, Day day) {
        super("workout viewer", caller);
        body = new JPanel();
        body.setLayout(new GridLayout(9,1));
        this.day = day;
        this.app = app;
        if (day.getWorkout() != null) {
            viewer();
        } else {
            JOptionPane.showMessageDialog(this, "No workout created");
            new DayViewerFrame(this, app, day);
        }

    }

    private void viewer() {
        back = new JButton("back");
        back.addActionListener(this);
        this.add(back, BorderLayout.SOUTH);
        List<Exercise> exerciseList = day.getWorkout().getExercises();
        for (Exercise e: exerciseList) {
            JPanel exercisePanel = new JPanel();
            JLabel exerciseName = new JLabel("Exercise: " + e.getName());
            JLabel exerciseSets = new JLabel("      sets: " + String.valueOf(e.getSets()));
            JLabel exerciseReps = new JLabel("      reps: " + String.valueOf(e.getReps()));
            exercisePanel.add(exerciseName);
            exercisePanel.add(exerciseSets);
            exercisePanel.add(exerciseReps);
            body.add(exercisePanel);
        }
        JScrollPane scrollPane = new JScrollPane(body,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            new DayViewerFrame(this, app, day);
        }
    }
}
