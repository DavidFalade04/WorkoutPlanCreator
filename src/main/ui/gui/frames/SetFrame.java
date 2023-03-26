package ui.gui.frames;

import model.Day;
import ui.gui.WorkoutCreatorAppGui;
import ui.gui.tools.AppFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Frame for setting nae of workout
public class SetFrame extends AppFrame implements ActionListener {
    JTextField input;
    JButton enter;
    WorkoutCreatorAppGui app;
    Day day;

    //MODIFIES: this
    //EFFECTS: creates frame
    public SetFrame(JFrame caller, WorkoutCreatorAppGui app, Day day) {
        super("setFrame", caller);

        input = new JTextField("Enter Workout name");
        enter = new JButton("Create");
        enter.addActionListener(this);
        JPanel body = new JPanel();
        this.app = app;
        this.day = day;

        body.add(input);
        body.add(enter);

        this.add(body, BorderLayout.CENTER);

    }

    @Override
    //MODIFIES: this
    //EFFECTS: reads user input
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enter) {
            new WorkoutWorkshopFrame(this, app, day, input.getText());
        }
    }
}
