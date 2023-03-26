package ui.gui.frames;

import ui.gui.WorkoutCreatorAppGui;
import ui.gui.tools.AppButton;
import ui.gui.tools.AppFrame;
import ui.gui.tools.Title;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Index frame of app
public class IndexFrame extends AppFrame implements ActionListener {

    JButton create;
    JButton load;
    JButton quit;
    WorkoutCreatorAppGui app;

    //EFFECTS: produce an index page/main menu
    public IndexFrame(WorkoutCreatorAppGui app) {
        super("WorkoutPlan Creator");
        this.app = app;

        this.setTitle("index");

        JPanel indexOptions = new JPanel();
        indexOptions.setPreferredSize(new Dimension(350, 300));
        indexOptions.setBackground(Color.red);
        spacerLR(indexOptions);
        indexOptions.setLayout(new GridLayout(3,1));
        indexOptions.setBorder(new EmptyBorder(30,0,50,0));

        create = new AppButton("Create New Plan");
        load = new AppButton("Load Saved Plans");
        quit = new AppButton("Quit App");

        create.addActionListener(this);
        load.addActionListener(this);
        quit.addActionListener(this);


        indexOptions.add(create);
        indexOptions.add(load);
        indexOptions.add(quit);


    }

    //MODIFIES: this
    //EFFECTS: spacer for menu buttons
    private void spacerLR(JPanel indexOptions) {
        JPanel leftSpacer = new JPanel();
        JPanel rightSpacer = new JPanel();
        leftSpacer.setPreferredSize(new Dimension(100,400));
        rightSpacer.setPreferredSize(new Dimension(100,400));
        this.add(leftSpacer, BorderLayout.WEST);
        this.add(rightSpacer,BorderLayout.EAST);
        this.add(indexOptions, BorderLayout.CENTER);
    }


    @Override
    //MODIFIES: this
    //EFFECTS: listens to user input
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            new CreateFrame(this, app);

        }
    }
}
