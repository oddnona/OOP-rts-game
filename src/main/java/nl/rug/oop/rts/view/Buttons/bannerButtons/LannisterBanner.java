package nl.rug.oop.rts.view.Buttons.bannerButtons;

import java.awt.*;

/**
 * button for the lannister house.
 */
public class LannisterBanner extends BannerButton {
    /**
     * constructor for the class.
     * @param location the position on screen.
     */
    public LannisterBanner(Point location) {
        super(location);
        idle = "/images/buttons/lanban.png";
        pressed = "/images/buttonsSelected/lanbanon.png";
        setupButtons();
    }
}
