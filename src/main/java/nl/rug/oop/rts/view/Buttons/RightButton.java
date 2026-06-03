package nl.rug.oop.rts.view.Buttons;

import javax.swing.*;
import java.awt.*;

/**
 * button class for the arrow pointing to the right.
 */
public class RightButton extends JButton {
    /**
     * size of the button.
     */
    private static final int TARGET_WIDTH = 100;

    /**
     * constructor for the class.
     */
    public RightButton() {
        ImageIcon idleBig    = new ImageIcon(getClass().getResource("/images/buttons/rightarrow.png"));
        ImageIcon pressedBig = new ImageIcon(getClass().getResource("/images/buttonsSelected/rightarrowON.png"));
        Image  idleScaled    = idleBig.getImage().getScaledInstance(TARGET_WIDTH, -1, Image.SCALE_SMOOTH);
        Image  pressedScaled = pressedBig.getImage().getScaledInstance(TARGET_WIDTH, -1, Image.SCALE_SMOOTH);
        ImageIcon idle    = new ImageIcon(idleScaled);
        ImageIcon pressed = new ImageIcon(pressedScaled);
        setIcon(idle);
        setPressedIcon(pressed);
        // setMaximumSize(d);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
        setIcon(idle);
        setPressedIcon(pressed);
    }
}
