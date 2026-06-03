package nl.rug.oop.rts.view.regionViews;

import nl.rug.oop.rts.model.regions.Region;
import nl.rug.oop.rts.view.TextureLoader;

import java.awt.*;
import java.nio.file.Path;

/**
 * region where Stormlands is drawn.
 */
public class StormlandsRegionView extends RegionView{
    /**
     * constructor for the region.
     * @param region the region model.
     */
    public StormlandsRegionView(Region region) {
        super(region);
        panel = new Panel(region.getGraph());
        panel.setMapWidth(7372);
        panel.setMapHeight(5529);
        panel.setPosition(new Point(0, -4140));
        panel.setBackground(TextureLoader.getInstance().getTexture(
                Path.of("images", "maps", "stormlands.jpg"), panel.getMapWidth(), panel.getMapHeight()
        ));
        panel.setNodeImage(TextureLoader.getInstance().getTexture(
                Path.of("images", "nodes", "stormsend.png"), 274, 450
        ));
        position = new Point(1656, 2358);
        panel.setSongName("OrnnStorms.wav");
    }
}
