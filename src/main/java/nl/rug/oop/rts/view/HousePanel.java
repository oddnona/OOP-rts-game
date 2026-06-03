package nl.rug.oop.rts.view;

import nl.rug.oop.rts.view.Buttons.bannerButtons.*;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * class that represents the house panel. where the user chooses what house he belongs in the game.
 */
public class HousePanel extends JPanel {
    /**
     * list of buttons as options.
     */
    private List<BannerButton> bannerButtons;
    /**
     * background.
     */
    private Image background;

    /**
     * constructor for the house panel.
     */
    public HousePanel() {
        setLayout(null);
        background = TextureLoader.getInstance().getTexture(
                Path.of("images", "maps", "choicescreen.jpg"), 2000, 1010
        );
        bannerButtons = new ArrayList<>();
        initBanners();
        for(BannerButton b : bannerButtons) {
            b.setBounds(b.getPosition().x, b.getPosition().y, 200, 300);
            add(b);
        }
    }

    /**
     * initializes the banner buttons.
     */
    private void initBanners() {
        bannerButtons.add(new ArrynBanner(new Point(250, 100)));
        bannerButtons.add(new LannisterBanner(new Point(1450, 600)));
        bannerButtons.add(new StarkBanner(new Point(850, 630)));
        bannerButtons.add(new FreeBanner(new Point(100, 440)));
        bannerButtons.add(new TyrellBanner(new Point(1200, 700)));
        bannerButtons.add(new BraavosiBanner(new Point(280, 580)));
        bannerButtons.add(new MartellBanner(new Point(500, 700)));
        bannerButtons.add(new GreyjoyBanner(new Point(40, 170)));
        bannerButtons.add(new YunkaiBanner(new Point(1350, 40)));
        bannerButtons.add(new BaratheonBanner(new Point(1550, 170)));
        bannerButtons.add(new DothrakiBanner(new Point(1650, 440)));
    }

    /**
     * paint component for the background.
     * @param g the <code>Graphics</code> object to protect
     */

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }

    /**
     * getter for the list of buttons.
     * @return the list of buttons.
     */
    public List<BannerButton> getButtons() {
        return bannerButtons;
    }
}
