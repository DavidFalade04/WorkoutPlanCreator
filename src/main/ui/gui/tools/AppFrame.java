package ui.gui.tools;

import javax.swing.*;
import java.awt.*;

//Extension of JFrame class with default options for GUI frames
public class AppFrame extends JFrame {

    JFrame callee;

    public AppFrame(String title) {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        JPanel titlePanel = new Title(title);
        this.callee = callee;
        this.add(titlePanel, BorderLayout.NORTH);
    }


}
