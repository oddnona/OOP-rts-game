package nl.rug.oop.rts.model.nodes;

import java.awt.*;

/**
 * army camps are the smallest in the view.
 */
public class ArmyCamp extends Node{
    /**
     * the constructor of the Node.
     *
     * @param name     the given name
     * @param position the position on the panel.
     */
    public ArmyCamp(String name, Point position) {
        super(name, position);
        range = 50;
        full = true;
    }

    @Override
    public Node cloneNode() {
        ArmyCamp clone = new ArmyCamp(name, new Point(position));
        clone.range = range;
        clone.full = full;
        clone.house = house;
        clone.id = id;
        return clone;
    }
}
