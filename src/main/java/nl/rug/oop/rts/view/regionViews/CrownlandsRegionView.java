package nl.rug.oop.rts.view.regionViews;

import nl.rug.oop.rts.model.regions.Region;
import nl.rug.oop.rts.view.TextureLoader;

import java.awt.*;
import java.nio.file.Path;

/**
 * region where Crownlands is drawn.
 */

public class CrownlandsRegionView extends RegionView{
    /**
     * constructor for the region.
     * @param region the region model.
     */
    public CrownlandsRegionView(Region region) {
        super(region);
        panel = new Panel(region.getGraph());
        panel.setMapWidth(8192);
        panel.setMapHeight(5529);
        panel.setPosition(new Point(-4680, 0));
        panel.setBackground(TextureLoader.getInstance().getTexture(
                Path.of("images", "maps", "crownlands.jpg"), panel.getMapWidth(), panel.getMapHeight()
        ));
        panel.setNodeImage(TextureLoader.getInstance().getTexture(
                Path.of("images", "nodes", "kingslanding.png"), 736, 765
        ));
        position = new Point(1502, 2038);
        panel.setSongName("MordKings.wav");
    }
}
