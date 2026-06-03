package nl.rug.oop.rts.view.regionViews;

import nl.rug.oop.rts.model.regions.Region;
import nl.rug.oop.rts.view.TextureLoader;

import java.awt.*;
import java.nio.file.Path;

/**
 * region where North is drawn.
 */
public class NorthRegionView extends RegionView{
    /**
     * constructor for the region.
     * @param region the region model.
     */
    public NorthRegionView(Region region) {
        super(region);
        panel = new Panel(region.getGraph());
        panel.setMapWidth(7372);
        panel.setMapHeight(5529);
        panel.setPosition(new Point(-5400, -4140));
        panel.setBackground(TextureLoader.getInstance().getTexture(
                Path.of("images", "maps", "north.jpg"), panel.getMapWidth(), panel.getMapHeight()
        ));
        panel.setNodeImage(TextureLoader.getInstance().getTexture(
                Path.of("images", "nodes", "winterfell.png"), 526, 373
        ));
        position = new Point(1202, 764);
        panel.setSongName("UdyrNorth.wav");
    }
}
