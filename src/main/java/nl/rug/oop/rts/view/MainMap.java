package nl.rug.oop.rts.view;

import nl.rug.oop.rts.model.Map;
import nl.rug.oop.rts.view.regionViews.*;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Main Map will draw the map of the whole universe. The player will choose what region to go to from here.
 */
public class MainMap extends JPanel implements OpenScrollObserver {
    /**
     * the map model.
     */
    private Map map;
    /**
     * the image of the map.
     */
    private Image background;
    /**
     * the position of the top corner on the screen.
     */
    private Point position;
    /**
     * the real width and height of the map.
     */
    private int mapWidth, mapHeight;
    /**
     * the list of regions to go to.
     */
    private List<RegionView> regionViews;
    /**
     * the scroll view.
     */
    private ScrollView scrollView;

    /**
     * constructor that sets up the main Map panel.
     * @param map the map model.
     */
    public MainMap(Map map) {
        setLayout(null);
        this.map = map;
        scrollView = new ScrollView(map.getScroll());
        scrollView.addObserver(this);
        mapWidth = 5652;
        mapHeight = 3682;
        background = TextureLoader.getInstance().getTexture(
                Path.of("images", "maps", "gotmap.jpg"), 5652, 3682
        );
        position = new Point(0, 0);
        regionViews = new ArrayList<>();
        setupRegions();
    }

    /**
     * initialises all the regions of the main map.
     */
    private void setupRegions() {
        regionViews.add(new BeyondWallRegionView(map.getRegions().get(0)));
        regionViews.add(new BraavosRegionView(map.getRegions().get(1)));
        regionViews.add(new CrownlandsRegionView(map.getRegions().get(2)));
        regionViews.add(new DorneRegionView(map.getRegions().get(3)));
        regionViews.add(new DothrakiRegionView(map.getRegions().get(4)));
        regionViews.add(new DragonstoneRegionView(map.getRegions().get(5)));
        regionViews.add(new IronIslandsRegionView(map.getRegions().get(6)));
        regionViews.add(new NorthRegionView(map.getRegions().get(7)));
        regionViews.add(new ReachRegionView(map.getRegions().get(8)));
        regionViews.add(new StormlandsRegionView(map.getRegions().get(9)));
        regionViews.add(new ValeRegionView(map.getRegions().get(10)));
        regionViews.add(new ValyriaRegionView(map.getRegions().get(11)));
        regionViews.add(new WesterlandsRegionView(map.getRegions().get(12)));
        regionViews.add(new YunkaiRegionView(map.getRegions().get(13)));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, position.x, position.y, null);
        for(RegionView regionView : regionViews) {
            regionView.draw(g);
        }
        scrollView.draw(g);
    }

    /**
     * getter for the scroll.
     * @return the scroll.
     */
    public ScrollView getScroll() {
        return scrollView;
    }

    /**
     * getter for the position of the map on the screen.
     * @return the current position.
     */
    public Point getPosition() {
        return position;
    }

    /**
     * setter for the position of the map.
     * @param position the new position.
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * getter for the width of the map.
     * @return the width of the map.
     */
    public int getMapWidth() {
        return mapWidth;
    }

    /**
     * getter for the height of the map.
     * @return the height of the map.
     */
    public int getMapHeight() {
        return mapHeight;
    }

    /**
     * getter for the regions of the map.
     * @return the list of regions.
     */
    public List<RegionView> getRegionViews() {
        return regionViews;
    }

    /**
     * getter for the map model.
     * @return the map model.
     */
    public Map getMap() {
        return map;
    }

    @Override
    public void update() {
        if(scrollView.isOpen()) {
            add(scrollView.getScrollPane());
        } else {
            remove(scrollView.getScrollPane());
        }
        revalidate();
        repaint();
    }
}
