package nl.rug.oop.rts.view.Buttons.bannerButtons;

import java.awt.*;

/**
 * button for the martell house.
 */
public class MartellBanner extends BannerButton {
    /**
     * constructor for the class.
     * @param p the position on screen.
     */
    public MartellBanner(Point p) {
        super(p);
        idle = "/images/buttons/martban.png";
        pressed = "/images/buttonsSelected/martbanon.png";
        setupButtons();
    }
}
