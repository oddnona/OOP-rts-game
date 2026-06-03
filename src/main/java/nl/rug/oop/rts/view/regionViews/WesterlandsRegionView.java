package nl.rug.oop.rts.view.regionViews;

import nl.rug.oop.rts.model.regions.Region;
import nl.rug.oop.rts.view.TextureLoader;

import java.awt.*;
import java.nio.file.Path;

/**
 * region where Westerlands is drawn.
 */
public class WesterlandsRegionView extends RegionView{
    /**
     * constructor for the region.
     * @param region the region model.
     */
    public WesterlandsRegionView(Region region) {
        super(region);
        panel = new Panel(region.getGraph());
        panel.setMapWidth(7372);
        panel.setMapHeight(5529);
        panel.setPosition(new Point(-5260, -3600));
        panel.setBackground(TextureLoader.getInstance().getTexture(
                Path.of("images", "maps", "westerlands.jpg"), panel.getMapWidth(), panel.getMapHeight()
        ));
        panel.setNodeImage(TextureLoader.getInstance().getTexture(
                Path.of("images", "nodes", "crock.png"), 447, 599
        ));
        position = new Point(750, 1951);
        panel.setSongName("RenataWest.wav");
    }
}
