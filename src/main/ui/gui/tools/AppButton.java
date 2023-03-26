package ui.gui.tools;

import javax.swing.*;
import java.awt.*;

//Extension of JButton with default behaviour
public class AppButton extends JButton {

    public AppButton (String text) {
        super(text);
        super.setBackground(Color.white);
        super.setFocusable(false);
        super.setFont(new Font("roboto",Font.PLAIN, 26));
    }
}
