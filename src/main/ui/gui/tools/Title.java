package ui.gui.tools;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

//Header for frames
public class Title extends JPanel {

    //EFFECTS: creates title with default behaviour for app
    public Title(String text) {
        JLabel title = new JLabel(text);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        this.setPreferredSize(new Dimension(500,100));
        this.setBorder(new EmptyBorder(30,10,10,10));
        this.add(title);
        this.setBackground(Color.ORANGE);
    }
}
