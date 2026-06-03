package nl.rug.oop.rts.view.Buttons.bannerButtons;

import java.awt.*;

/**
 * button for the baratheon army.
 */
public class BaratheonBanner extends BannerButton {
    /**
     * constructor for the class.
     * @param position the position on screen.
     */
    public BaratheonBanner(Point position) {
        super(position);
        idle = "/images/buttons/barban.png";
        pressed = "/images/buttonsSelected/barbanon.png";
        setupButtons();
    }
}
