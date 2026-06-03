package nl.rug.oop.rts.view.regionViews;

import nl.rug.oop.rts.model.regions.Region;
import nl.rug.oop.rts.view.TextureLoader;

import java.awt.*;
import java.nio.file.Path;

/**
 * region where Braavos is drawn.
 */
public class BraavosRegionView extends RegionView{
    /**
     * constructor for the region.
     * @param region the region model.
     */
    public BraavosRegionView(Region region) {
        super(region);
        panel = new Panel(region.getGraph());
        panel.setMapWidth(7372);
        panel.setMapHeight(5529);
        panel.setPosition(new Point(0, -4413));
        panel.setBackground(TextureLoader.getInstance().getTexture(
                Path.of("images", "maps", "braavos.jpg"), panel.getMapWidth(), panel.getMapHeight()
        ));
        panel.setNodeImage(TextureLoader.getInstance().getTexture(
                Path.of("images", "nodes", "titan.png"), 731, 731
        ));
        position = new Point(2193, 1401);
        panel.setSongName("YoneBraavos.wav");
    }
}
