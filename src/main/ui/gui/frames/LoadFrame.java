package ui.gui.frames;

import model.WorkoutPlan;
import ui.gui.WorkoutCreatorAppGui;
import ui.gui.tools.AppFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Frame for loading content
public class LoadFrame extends AppFrame implements ActionListener {
    WorkoutCreatorAppGui app;
    JPanel body;
    JComboBox comboBox;
    JButton back;

    public LoadFrame(JFrame caller, WorkoutCreatorAppGui app) {
        super("Load plans", caller);
        this.app = app;

        if (app.getPlans().isEmpty()) {
            JOptionPane.showMessageDialog(this,"no plans created :(");
            new IndexFrame(app, this);
        } else {
            load();
        }

    }

    private void load() {
        body = new JPanel();
        comboBox();
        back = new JButton("back");
        back.addActionListener(this);
        this.add(back, BorderLayout.SOUTH);

    }

    private void comboBox() {
        List<WorkoutPlan> plans = app.getPlans();
        List<String> planNames = new ArrayList<>();

        for (WorkoutPlan wp: plans) {
            planNames.add(wp.getName());
        }

        comboBox = new JComboBox<>(planNames.toArray());
        comboBox.addActionListener(this);

        body.add(comboBox);
        this.add(body);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBox) {
            int index = comboBox.getSelectedIndex();
            WorkoutPlan currentWp = app.getPlans().get(index);
            app.setCurrentWorkoutPlan(currentWp);
            new PlanViewerFrame(this, app, currentWp);
        }
        if (e.getSource() == back) {
            new IndexFrame(app, this);
        }
    }
}
