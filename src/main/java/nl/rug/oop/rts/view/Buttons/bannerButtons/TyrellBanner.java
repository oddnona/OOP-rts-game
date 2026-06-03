package nl.rug.oop.rts.view.Buttons.bannerButtons;

import java.awt.*;

/**
 * button for the tyrell house.
 */
public class TyrellBanner extends BannerButton {
    /**
     * constructor for the class.
     * @param p the position on screen.
     */
    public TyrellBanner(Point p) {
        super(p);
        idle = "/images/buttons/tyrban.png";
        pressed = "/images/buttonsSelected/tyrbanon.png";
        setupButtons();
    }
}
