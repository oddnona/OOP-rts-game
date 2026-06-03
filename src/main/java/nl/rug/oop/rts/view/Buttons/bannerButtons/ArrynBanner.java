package nl.rug.oop.rts.view.Buttons.bannerButtons;

import java.awt.*;

/**
 * button for the arryn house.
 */
public class ArrynBanner extends BannerButton {
    /**
     * constructor for the class.
     * @param position the position on screen.
     */
    public ArrynBanner(Point position) {
        super(position);
        idle = "/images/buttons/arrban.png";
        pressed = "/images/buttonsSelected/arrbanon.png";
        setupButtons();
    }
}
