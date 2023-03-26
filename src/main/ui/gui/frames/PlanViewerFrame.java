package ui.gui.frames;

import model.*;
import model.WorkoutPlan;
import ui.gui.WorkoutCreatorAppGui;
import ui.gui.tools.AppFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Frame for viewing a workout plan
public class PlanViewerFrame extends AppFrame implements ActionListener {
    Day monday;
    Day tuesday;
    Day wednesday;
    Day thursday;
    Day friday;
    Day saturday;
    Day sunday;
    
    JPanel mondayPanel;
    JPanel tuesdayPanel;
    JPanel wednesdayPanel;
    JPanel thursdayPanel;
    JPanel fridayPanel;
    JPanel saturdayPanel;
    JPanel sundayPanel;

    JButton viewMon = new JButton("view");
    JButton viewTues = new JButton("view");
    JButton viewWed = new JButton("view");
    JButton viewThurs = new JButton("view");
    JButton viewFri = new JButton("view");
    JButton viewSat = new JButton("view");
    JButton viewSun = new JButton("view");

    JButton back;

    WorkoutPlan workoutPlan;
    WorkoutCreatorAppGui app;
    

    //EFFECTS: creates frame and initializes fields
    public PlanViewerFrame(JFrame caller, WorkoutCreatorAppGui app, WorkoutPlan workoutPlan) {
        super("Viewing " + workoutPlan.getName(), caller);

        this.workoutPlan = workoutPlan;
        this.app = app;
        JPanel body = new JPanel();
        body.setLayout(new GridLayout(7,1));

        loadDays(workoutPlan);

        createDayPanels();
        panelText();
        panelButton();

        body.add(mondayPanel);
        body.add(tuesdayPanel);
        body.add(wednesdayPanel);
        body.add(thursdayPanel);
        body.add(fridayPanel);
        body.add(saturdayPanel);
        body.add(sundayPanel);

        back = new JButton("back");
        back.addActionListener(this);
        this.add(back, BorderLayout.SOUTH);


        this.add(body);




    }

    //MODIFIES: this
    //EFFECTS: adds buttons
    private void panelButton() {
        viewMon.addActionListener(this);
        viewTues.addActionListener(this);
        viewWed.addActionListener(this);
        viewThurs.addActionListener(this);
        viewFri.addActionListener(this);
        viewSat.addActionListener(this);
        viewSun.addActionListener(this);

        mondayPanel.add(viewMon);
        tuesdayPanel.add(viewTues);
        wednesdayPanel.add(viewWed);
        thursdayPanel.add(viewThurs);
        fridayPanel.add(viewFri);
        saturdayPanel.add(viewSat);
        sundayPanel.add(viewSun);
    }

    //MODIFIES: this
    //EFFECTS: adds panels
    private void panelText() {
        mondayPanel.add(new JLabel("day: " + monday.getName() + " Status: " + monday.getStatus()));
        tuesdayPanel.add(new JLabel("day: " + tuesday.getName() + " Status: " + tuesday.getStatus()));
        wednesdayPanel.add(new JLabel("day: " + wednesday.getName() + " Status: " + wednesday.getStatus()));
        thursdayPanel.add(new JLabel("day: " + thursday.getName() + " Status: " + thursday.getStatus()));
        fridayPanel.add(new JLabel("day: " + friday.getName() + " Status: " + friday.getStatus()));
        saturdayPanel.add(new JLabel("day: " + saturday.getName() + " Status: " + saturday.getStatus()));
        sundayPanel.add(new JLabel("day: " + sunday.getName() + " Status: " + sunday.getStatus()));
    }

    //MODIFIES: this
    //EFFECTS: creates day panels
    private void createDayPanels() {
        mondayPanel = new JPanel();
        mondayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        tuesdayPanel = new JPanel();
        tuesdayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        wednesdayPanel = new JPanel();
        wednesdayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        thursdayPanel = new JPanel();
        thursdayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        fridayPanel = new JPanel();
        fridayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        saturdayPanel = new JPanel();
        saturdayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        sundayPanel = new JPanel();
        sundayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
    }

    //MODIFIES: this
    //EFFECTS: loads the days of a workout into frame
    private void loadDays(WorkoutPlan workoutPlan) {
        monday = workoutPlan.getDay("monday");
        tuesday = workoutPlan.getDay("tuesday");
        wednesday = workoutPlan.getDay("wednesday");
        thursday = workoutPlan.getDay("thursday");
        friday = workoutPlan.getDay("friday");
        saturday = workoutPlan.getDay("saturday");
        sunday = workoutPlan.getDay("sunday");
    }

    @Override
    //MODIFIES: this
    //EFFECTS: listens to user input
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewMon) {
            new DayViewerFrame(this, app, workoutPlan.getDay("monday"));
        } else if (e.getSource() == viewTues) {
            new DayViewerFrame(this, app, workoutPlan.getDay("tuesday"));
        } else if (e.getSource() == viewWed) {
            new DayViewerFrame(this, app, workoutPlan.getDay("wednesday"));
        } else if (e.getSource() == viewThurs) {
            new DayViewerFrame(this, app, workoutPlan.getDay("thursday"));
        } else if (e.getSource() == viewFri) {
            new DayViewerFrame(this, app, workoutPlan.getDay("friday"));
        } else if (e.getSource() == viewSat) {
            new DayViewerFrame(this, app, workoutPlan.getDay("saturday"));
        } else if (e.getSource() == viewSun) {
            new DayViewerFrame(this, app, workoutPlan.getDay("sunday"));
        } else if (e.getSource() == back) {
            new IndexFrame(app, this);
        }
    }
}
