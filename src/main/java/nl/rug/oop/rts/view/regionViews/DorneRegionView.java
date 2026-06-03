package nl.rug.oop.rts.view.regionViews;

import nl.rug.oop.rts.model.regions.Region;
import nl.rug.oop.rts.view.TextureLoader;

import java.awt.*;
import java.nio.file.Path;

/**
 * region where Dorne is drawn.
 */
public class DorneRegionView extends RegionView{
    /**
     * constructor for the region.
     * @param region the region model.
     */
    public DorneRegionView(Region region) {
        super(region);
        panel = new Panel(region.getGraph());
        panel.setMapWidth(8638);
        panel.setMapHeight(5479);
        panel.setPosition(new Point(-2800, -4213));
        panel.setBackground(TextureLoader.getInstance().getTexture(
                Path.of("images", "maps", "dorne.jpg"), panel.getMapWidth(), panel.getMapHeight()
        ));
        panel.setNodeImage(TextureLoader.getInstance().getTexture(
                Path.of("images", "nodes", "sunspear.png"),433 , 621
        ));
        position = new Point(1796, 2863);
        panel.setSongName("QiyanaDorne.wav");
    }
}
