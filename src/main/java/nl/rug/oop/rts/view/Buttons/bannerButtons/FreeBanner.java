package nl.rug.oop.rts.view.Buttons.bannerButtons;

import java.awt.*;

/**
 * button for the free folk tribe.
 */
public class FreeBanner extends BannerButton {
    /**
     * constructor for the class.
     * @param location the position on screen.
     */
    public FreeBanner(Point location) {
        super(location);
        idle = "/images/buttons/ffban.png";
        pressed = "/images/buttonsSelected/ffbanon.png";
        setupButtons();
    }
}
