package nl.rug.oop.rts.view.Buttons.bannerButtons;

import java.awt.*;

/**
 * button for the stark house.
 */
public class StarkBanner extends BannerButton {
    /**
     * constructor for the class.
     * @param p the position on screen.
     */
    public StarkBanner(Point p) {
        super(p);
        idle = "/images/buttons/starkban.png";
        pressed = "/images/buttonsSelected/starkbanon.png";
        setupButtons();
    }
}
