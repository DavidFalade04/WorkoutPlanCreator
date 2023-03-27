package ui.gui.frames;

import model.Day;
import ui.gui.WorkoutCreatorAppGui;
import ui.gui.tools.AppButton;
import ui.gui.tools.AppFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Frame for viewing a day in a workout plan
public class DayViewerFrame extends AppFrame implements ActionListener {
    WorkoutCreatorAppGui app;
    Day day;

    JButton set;
    JButton edit;
    JButton view;

    JLayeredPane layers;
    JPanel body;
    JPanel input;

    JButton create;
    JButton back;
    JTextField inputText;

    //MODIFIES: this
    //EFFECTS: creates frame
    public DayViewerFrame(JFrame caller, WorkoutCreatorAppGui app, Day day) {
        super("Viewing " + day.getName(), caller);
        this.app = app;
        this.day = day;
        JPanel desc = new JPanel();
        desc.add(new JLabel("Status: " + day.getStatus()));

        String goal;
        if (null == day.getWorkout()) {
            goal = "rest";
        } else {
            goal = day.getWorkout().getWorkoutGoal();
        }

        desc.add(new JLabel("Workout Goal: " + goal));
        desc.setPreferredSize(new Dimension(500,100));
        body = new JPanel();
        body.setLayout(new GridLayout(3,1));
        Box box = new Box(10);
        JLayeredPane layers = new JLayeredPane();
        layers.setBounds(0,0,500,500);
        layers.add(body);

        back(desc);
        createButtons(desc, body);

        this.add(body, BorderLayout.CENTER);

    }

    //EFFECTS: creates back button
    private void back(JPanel desc) {
        back = new JButton("back");
        back.addActionListener(this);
        this.add(back, BorderLayout.SOUTH);
    }

    //MODIFIES: this
    //EFFECTS: creates buttons
    private void createButtons(JPanel desc, JPanel body) {
        body.add(desc);
        if (day.getWorkout() == null) {
            set = new AppButton("Set Workout");
            set.addActionListener(this);
            body.add(set);
        } else {
            edit = new AppButton("Edit Workout");
            edit.addActionListener(this);
            body.add(edit);
        }

        view = new AppButton("View exercises");
        view.addActionListener(this);
        body.add(view);
    }


    @Override
    //MODIFIES: this
    //EFFECTS: listens to user input
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == set) {
            new SetFrame(this, app, day);
            //new WorkoutWorkshopFrame(this, app, day);
        }
        if (e.getSource() == view) {
            new WorkoutViewer(this, app, day);
        }
        if (e.getSource() == edit) {
            new WorkoutWorkshopFrame(this, app, day);
        }
        if (e.getSource() == back) {
            new PlanViewerFrame(this, app, app.getCurrentWorkoutPlan());
        }

    }
}
