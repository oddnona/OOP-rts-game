package nl.rug.oop.rts.view.regionViews;

import nl.rug.oop.rts.model.regions.Region;

import java.awt.*;

/**
 * class that represents a region on the map.
 */
public abstract class RegionView {
    /**
     * the region model.
     */
    protected Region region;
    /**
     * the panel that leads you to the regions territory.
     */
    protected Panel panel;
    /**
     * the position on the main map.
     */
    protected Point position;
    /**
     * bool that state if the region is selectable or not.
     */
    protected boolean selectable;

    /**
     * constructor for the region.
     * @param region the region model.
     */
    public RegionView(Region region) {
        this.region = region;
    }

    /**
     * draws the region on the map if it is selectable.
     * @param g the graphics object to draw on the screen.
     */
    public void draw(Graphics g) {
        if(selectable) {
            g.setColor(Color.RED);
            g.drawOval(position.x - 50, position.y - 50, 100, 100);
        }
    }

    /**
     * getter for the panel.
     * @return the current panel.
     */
    public Panel getPanel() {
        return panel;
    }

    /**
     * setter for the panel.
     * @param panel the new panel.
     */
    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    /**
     * getter for the position.
     * @return the current position.
     */
    public Point getPosition() {
        return position;
    }

    /**
     * setter for the position.
     * @param position the new position.
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * setter for the selectable state.
     * @param selectable the new selectable state.
     */
    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    /**
     * getter for the region model.
     * @return the region model.
     */
    public Region getRegion() {
        return region;
    }
}
