package ui.gui.tools;

import ui.gui.frames.CreateFrame;

import javax.swing.*;
import java.awt.*;

//Extension of JFrame class with default options for GUI frames
public abstract class AppFrame extends JFrame {

    JFrame caller;

    //EFFECTS: creates appFrame with default behaviour and a caller
    public AppFrame(String title, JFrame caller) {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        JPanel titlePanel = new Title(title);
        this.caller = caller;
        this.add(titlePanel, BorderLayout.NORTH);
        caller.dispose();
    }

    //EFFECTS: creates appFrame with default behaviour with caller set as self
    public AppFrame(String title) {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        JPanel titlePanel = new Title(title);
        this.caller = this;
        this.add(titlePanel, BorderLayout.NORTH);
    }



}
