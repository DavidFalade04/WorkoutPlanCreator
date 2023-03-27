package ui.gui.tools;

import javax.swing.*;
import java.awt.*;

//Extension of JButton with default behaviour
public class AppButton extends JButton {

    //MODIFIES: this
    //EFFECTS: standard button for app
    public AppButton (String text) {
        super(text);
        super.setBackground(Color.white);
        super.setFocusable(false);
        super.setFont(new Font("roboto",Font.PLAIN, 26));
    }

    //MODIFIES: this
    //EFFECTS: standard button for app
    public AppButton (Icon icon) {
        super(icon);
        super.setBackground(Color.white);
        super.setFocusable(false);
        super.setFont(new Font("roboto",Font.PLAIN, 26));
    }
}
