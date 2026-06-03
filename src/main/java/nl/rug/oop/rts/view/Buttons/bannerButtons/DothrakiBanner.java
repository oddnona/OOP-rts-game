package nl.rug.oop.rts.view.Buttons.bannerButtons;

import java.awt.*;

/**
 * button for the dothraki tribe.
 */
public class DothrakiBanner extends BannerButton {
    /**
     * constructor for the class.
     * @param p the position on screen.
     */
    public DothrakiBanner(Point p) {
        super(p);
        idle = "/images/buttons/doban.png";
        pressed = "/images/buttonsSelected/dobanon.png";
        setupButtons();
    }
}
