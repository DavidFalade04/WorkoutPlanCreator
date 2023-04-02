package ui.gui.frames;

import model.Event;
import model.EventLog;
import ui.gui.WorkoutCreatorAppGui;
import ui.gui.tools.AppButton;
import ui.gui.tools.AppFrame;
import ui.gui.tools.Title;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

// Index frame of app
public class IndexFrame extends AppFrame implements ActionListener {

    JButton create;
    JButton load;
    JButton quit;
    JButton diskLoad;
    JButton diskSave;
    WorkoutCreatorAppGui app;

    //EFFECTS: produce an index page/main menu
    public IndexFrame(WorkoutCreatorAppGui app, JFrame caller) {
        super("WorkoutPlan Creator");
        this.app = app;
        this.setTitle("index");
        if (caller != null) {
            caller.dispose();
        }

        JPanel indexOptions = new JPanel();
        indexOptions.setPreferredSize(new Dimension(350, 300));
        spacerLR(indexOptions);
        indexOptions.setLayout(new GridLayout(4,1));
        indexOptions.setBorder(new EmptyBorder(30,0,50,0));

        createButtons();

        JPanel disk = new JPanel(new GridLayout(1,2));
        disk.add(diskLoad);
        disk.add(diskSave);



        indexOptions.add(create);
        indexOptions.add(load);
        indexOptions.add(quit);
        indexOptions.add(disk);


    }


    //MODIFIES: this
    //EFFECT: creates buttons
    private void createButtons() {
        create = new AppButton("Create New Plan");
        load = new AppButton("Load Saved Plans");
        quit = new AppButton("Quit App");
        diskLoad = new AppButton("Disk Load");
        diskLoad.setFont(new Font("roboto",Font.PLAIN, 20));
        diskSave = new AppButton("Disk Save");
        diskSave.setFont(new Font("roboto",Font.PLAIN, 20));


        create.addActionListener(this);
        load.addActionListener(this);
        quit.addActionListener(this);
        diskLoad.addActionListener(this);
        diskSave.addActionListener(this);
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
        if (e.getSource() == load) {
            new LoadFrame(this, app);
        }
        if (e.getSource() == diskLoad) {
            app.load();
            JOptionPane.showMessageDialog(this, "Loaded from Disk");
        }
        if (e.getSource() == diskSave) {
            app.save();
            JOptionPane.showMessageDialog(this, "Saved to disk");
        }
        if (e.getSource() == quit) {
            this.dispose();
            Iterator<Event> events = EventLog.getInstance().iterator();

            for (Iterator<Event> it = events; it.hasNext(); ) {
                Event event = it.next();
                System.out.println(event.getDescription());


            }
        }
    }
}
