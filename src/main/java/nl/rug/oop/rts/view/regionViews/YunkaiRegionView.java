package nl.rug.oop.rts.view.regionViews;

import nl.rug.oop.rts.model.regions.Region;
import nl.rug.oop.rts.view.TextureLoader;

import java.awt.*;
import java.nio.file.Path;

/**
 * region where Yunkai is drawn.
 */
public class YunkaiRegionView extends RegionView{
    /**
     * constructor for the region.
     * @param region the region model.
     */
    public YunkaiRegionView(Region region ) {
        super(region);
        panel = new Panel(region.getGraph());
        panel.setMapWidth(7372);
        panel.setMapHeight(5529);
        panel.setPosition(new Point(-5022, -4059));
        panel.setBackground(TextureLoader.getInstance().getTexture(
                Path.of("images", "maps", "astapor.jpg"), panel.getMapWidth(), panel.getMapHeight()
        ));
        panel.setNodeImage(TextureLoader.getInstance().getTexture(
                Path.of("images", "nodes", "harpy.png"), 360, 480
        ));
        position = new Point(4035, 2702);
        panel.setSongName("CassAstapor.wav");
    }
}
