package nl.rug.oop.rts.view.regionViews;

import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.events.TrapedBattlefieldEvent;
import nl.rug.oop.rts.model.nodes.ArmyCamp;
import nl.rug.oop.rts.model.nodes.Settlement;
import nl.rug.oop.rts.model.nodes.Throne;
import nl.rug.oop.rts.model.regions.Region;
import nl.rug.oop.rts.model.soldiertypes.TyrellSoldier;
import nl.rug.oop.rts.view.TextureLoader;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * region where The Beyond the Wall is drawn.
 */
public class BeyondWallRegionView extends RegionView {
    /**
     * constructor for the region.
     * @param region the region model.
     */
    public BeyondWallRegionView(Region region) {
        super(region);
        panel = new Panel(region.getGraph());
        panel.setMapWidth(7372);
        panel.setMapHeight(5529);
        panel.setPosition(new Point(-5000, 0));
        panel.setBackground(TextureLoader.getInstance().getTexture(
                Path.of("images", "maps", "wild.jpg"), panel.getMapWidth(), panel.getMapHeight()
        ));
        panel.setNodeImage(TextureLoader.getInstance().getTexture(
                Path.of("images", "nodes", "camp.png"), 397, 528
        ));
        position = new Point(1311, 146);
        panel.setSongName("LissWall.wav");
    }
}
