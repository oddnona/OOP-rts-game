package nl.rug.oop.rts.view.regionViews;

import nl.rug.oop.rts.model.regions.Region;
import nl.rug.oop.rts.view.TextureLoader;

import java.awt.*;
import java.nio.file.Path;

/**
 * region where The Reach is drawn.
 */
public class ReachRegionView extends RegionView{
    /**
     * constructor for the region.
     * @param region the region model.
     */
    public ReachRegionView(Region region) {
        super(region);
        panel = new Panel(region.getGraph());
        panel.setMapWidth(7500);
        panel.setMapHeight(5718);
        panel.setPosition(new Point(-5063, -4713));
        panel.setBackground(TextureLoader.getInstance().getTexture(
                Path.of("images", "maps", "reach.jpg"), panel.getMapWidth(), panel.getMapHeight()
        ));
        panel.setNodeImage(TextureLoader.getInstance().getTexture(
                Path.of("images", "nodes", "highgarden.png"), 481, 640
        ));
        position = new Point(914, 2458);
        panel.setSongName("MelReach.wav");
    }
}
