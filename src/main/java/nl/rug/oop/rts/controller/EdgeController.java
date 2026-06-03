package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.view.EdgeView;
import nl.rug.oop.rts.view.Sounds;

import java.awt.*;

/**
 * controller for the edge.
 */
public class EdgeController implements MouseObserver{
    /**
     * the view of an edge.
     */
    private EdgeView edgeView;
    /**
     * the edge.
     */
    private Edge edge;
    /**
     * sounds.
     */
    private Sounds sounds;

    /**
     * constructor for the controller.
     * @param edgeView the view of the edge.
     * @param edge the edge.
     */
    public EdgeController(EdgeView edgeView, Edge edge) {
        this.edgeView = edgeView;
        this.edge = edge;
        sounds = new Sounds();
    }

    @Override
    public void click(Point position) {
        int x = (position.x - edge.getMiddle().x);
        int y = (position.y - edge.getMiddle().y);
        if(Math.sqrt(x * x + y * y) <= (double)(edge.getRange() / 2)) {
            edge.setSelected(!edge.isSelected());
            sounds.playSound("buttonsound.wav");
        } else {
            edge.setSelected(false);
        }
    }

    @Override
    public void drag(Point position) {

    }

    @Override
    public void drag(int xOffset, int yOffset) {

    }

    @Override
    public void hover(Point position) {
        int x = (position.x - edge.getMiddle().x);
        int y = (position.y - edge.getMiddle().y);
        if(Math.sqrt(x * x + y * y) <= (double)(edge.getRange() / 2)) {
            edge.setSelectable(true);
        } else {
            edge.setSelectable(false);
        }
    }

    @Override
    public void refresh() {

    }
}