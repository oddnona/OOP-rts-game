package nl.rug.oop.rts.view.Buttons.bannerButtons;

import javax.swing.*;
import java.awt.*;

/**
 * Banner house buttons.
 */
public abstract class BannerButton extends JButton {
    /**
     * size of the button.
     */
    protected static int TARGET_WIDTH = 200;

    /**
     * strings for the images' directories.
     */
    protected String idle, pressed;
    /**
     * coords.
     */
    protected Point position;

    /**
     * constructor for the banner button.
     * @param position the coords.
     */
    public BannerButton(Point position) {
        this.position = position;
    }

    /**
     * sets up the images and dimensions of the button.
     */
    protected void setupButtons() {
        ImageIcon idleBig = new ImageIcon(getClass().getResource(idle));
        ImageIcon pressedBig = new ImageIcon(getClass().getResource(pressed));
        Image idleScaled = idleBig.getImage().getScaledInstance(TARGET_WIDTH, -1, Image.SCALE_SMOOTH);
        Image pressedScaled = pressedBig.getImage().getScaledInstance(TARGET_WIDTH, -1, Image.SCALE_SMOOTH);
        ImageIcon idle = new ImageIcon(idleScaled);
        ImageIcon pressed = new ImageIcon(pressedScaled);
        setIcon(idle);
        setRolloverIcon(pressed);
        // setMaximumSize(d);        // to stop it from squeezing
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
        setIcon(idle);
        setPressedIcon(pressed);
    }

    /**
     * getter for the position on the screen.
     * @return the position on the screen.
     */
    public Point getPosition() {
        return position;
    }
}
