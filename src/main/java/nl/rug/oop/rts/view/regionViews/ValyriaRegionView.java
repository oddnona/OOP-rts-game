package nl.rug.oop.rts.view.regionViews;

import nl.rug.oop.rts.model.regions.Region;
import nl.rug.oop.rts.view.TextureLoader;

import java.awt.*;
import java.nio.file.Path;

/**
 * region where Old Valyria is drawn.
 */
public class ValyriaRegionView extends RegionView{
    /**
     * constructor for the region.
     * @param region the region model.
     */
    public ValyriaRegionView(Region region){
        super(region);
        panel = new Panel(region.getGraph());
        panel.setMapWidth(7542);
        panel.setMapHeight(3702);
        panel.setPosition(new Point(-1200, -2600));
        panel.setBackground(TextureLoader.getInstance().getTexture(
                Path.of("images", "maps", "valyria.jpg"), panel.getMapWidth(), panel.getMapHeight()
        ));
        panel.setNodeImage(TextureLoader.getInstance().getTexture(
                Path.of("images", "nodes", "ruins.png"), 461, 645
        ));
        position = new Point(3319, 3418);
        panel.setSongName("KindredRuins.wav");
    }
}
