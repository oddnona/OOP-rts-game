package nl.rug.oop.rts.view.Buttons.bannerButtons;

import java.awt.*;

/**
 * button for a braavosi house.
 */
public class BraavosiBanner extends BannerButton {
    /**
     * constructor for the class.
     * @param p the position on screen.
     */
    public BraavosiBanner(Point p) {
        super(p);
        idle = "/images/buttons/bravban.png";
        pressed = "/images/buttonsSelected/bravbanon.png";
        setupButtons();
    }
}
