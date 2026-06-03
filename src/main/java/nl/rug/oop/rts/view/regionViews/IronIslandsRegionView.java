package nl.rug.oop.rts.view.regionViews;

import nl.rug.oop.rts.model.regions.Region;
import nl.rug.oop.rts.view.TextureLoader;

import java.awt.*;
import java.nio.file.Path;

/**
 * region where Iron Islands is drawn.
 */
public class IronIslandsRegionView extends RegionView{
    /**
     * constructor for the region.
     * @param region the region model.
     */
    public IronIslandsRegionView(Region region) {
        super(region);
        panel = new Panel(region.getGraph());
        panel.setMapWidth(7372);
        panel.setMapHeight(5529);
        panel.setPosition(new Point(-4140, -540));
        panel.setBackground(TextureLoader.getInstance().getTexture(
                Path.of("images", "maps", "islands.jpg"), panel.getMapWidth(), panel.getMapHeight()
        ));
        panel.setNodeImage(TextureLoader.getInstance().getTexture(
                Path.of("images", "nodes", "pyke.png"), 368, 416
        ));
        position = new Point(744, 1629);
        panel.setSongName("PykeIron.wav");
    }
}
