package nl.rug.oop.rts.view.Buttons.bannerButtons;

import java.awt.*;

/**
 * button for the greyjoy house.
 */
public class GreyjoyBanner extends BannerButton {
    /**
     * constructor for the class.
     * @param location the position on screen.
     */
    public GreyjoyBanner(Point location) {
        super(location);
        idle = "/images/buttons/greyban.png";
        pressed = "/images/buttonsSelected/greybanon.png";
        setupButtons();
    }
}
