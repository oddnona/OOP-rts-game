package nl.rug.oop.rts.view.regionViews;

import nl.rug.oop.rts.model.regions.Region;
import nl.rug.oop.rts.view.TextureLoader;

import java.awt.*;
import java.nio.file.Path;

/**
 * region where The Vale of Arryn is drawn.
 */
public class ValeRegionView extends RegionView{
    /**
     * constructor for the region.
     * @param region the region model.
     */
    public ValeRegionView(Region region) {
        super(region);
        panel = new Panel(region.getGraph());
        panel.setMapWidth(7372);
        panel.setMapHeight(5529);
        panel.setPosition(new Point(-720, -4320));
        panel.setBackground(TextureLoader.getInstance().getTexture(
                Path.of("images", "maps", "vale.jpg"), panel.getMapWidth(), panel.getMapHeight()
        ));
        panel.setNodeImage(TextureLoader.getInstance().getTexture(
                Path.of("images", "nodes", "eyrie.png"), 258, 694
        ));
        position = new Point(1588, 1599);
        panel.setSongName("SwainVale.wav");
    }
}
