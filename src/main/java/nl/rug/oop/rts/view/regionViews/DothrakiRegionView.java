package nl.rug.oop.rts.view.regionViews;

import nl.rug.oop.rts.model.regions.Region;
import nl.rug.oop.rts.view.TextureLoader;

import java.awt.*;
import java.nio.file.Path;

/**
 * region where Dothraki Sea is drawn.
 */
public class DothrakiRegionView extends RegionView{
    /**
     * constructor for the region.
     * @param region the region model.
     */
    public DothrakiRegionView(Region region) {
        super(region);
        panel = new Panel(region.getGraph());
        panel.setMapWidth(7372);
        panel.setMapHeight(5529);
        panel.setPosition(new Point(-720, -3240));
        panel.setBackground(TextureLoader.getInstance().getTexture(
                Path.of("images", "maps", "dothrakisea.jpg"), panel.getMapWidth(), panel.getMapHeight()
        ));
        panel.setNodeImage(TextureLoader.getInstance().getTexture(
                Path.of("images", "nodes", "vaesdothrak.png"), 382, 287
        ));
        position = new Point(4755, 1797);
        panel.setSongName("GnarDothraki.wav");
    }
}
