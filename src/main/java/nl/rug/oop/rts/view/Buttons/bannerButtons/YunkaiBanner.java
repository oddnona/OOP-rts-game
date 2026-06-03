package nl.rug.oop.rts.view.Buttons.bannerButtons;

import java.awt.*;

/**
 * button for an astapor house.
 */
public class YunkaiBanner extends BannerButton {
    /**
     * constructor for the class.
     * @param p the position on screen.
     */
    public YunkaiBanner(Point p) {
        super(p);
        idle = "/images/buttons/unsban.png";
        pressed = "/images/buttonsSelected/unsbanon.png";
        setupButtons();
    }
}
