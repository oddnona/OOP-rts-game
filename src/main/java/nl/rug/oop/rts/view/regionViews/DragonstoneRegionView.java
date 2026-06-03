package nl.rug.oop.rts.view.regionViews;

import nl.rug.oop.rts.model.regions.Region;
import nl.rug.oop.rts.view.TextureLoader;

import java.awt.*;
import java.nio.file.Path;

/**
 * region where Dragonstone is drawn.
 */
public class DragonstoneRegionView extends RegionView {
    /**
     * constructor for the region.
     * @param region the region model.
     */
    public DragonstoneRegionView(Region region) {
        super(region);
        panel = new Panel(region.getGraph());
        panel.setMapWidth(7416);
        panel.setMapHeight(4544);
        panel.setPosition(new Point(-1040, -2600));
        panel.setBackground(TextureLoader.getInstance().getTexture(
                Path.of("images", "maps", "dragonstone.jpg"), panel.getMapWidth(), panel.getMapHeight()
        ));
        panel.setNodeImage(TextureLoader.getInstance().getTexture(
                Path.of("images", "nodes", "dragonstone.png"), 500, 500
        ));
        position = new Point(1765, 1925);
        panel.setSongName("KalistaDragon.wav");
    }
}
