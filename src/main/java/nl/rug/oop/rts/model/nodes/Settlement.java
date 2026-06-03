package nl.rug.oop.rts.model.nodes;

import java.awt.*;

/**
 * the settlement is the second largest.
 */
public class Settlement extends Node {
    /**
     * the constructor of the Node.
     *
     * @param name     the given name
     * @param position the position on the panel.
     */
    public Settlement(String name, Point position) {
        super(name, position);
        range = 500;
        full = false;
    }

    @Override
    public Node cloneNode() {
        Settlement clone = new Settlement(name, new Point(position));
        clone.range = range;
        clone.full = full;
        clone.house = house;
        clone.id = id;
        return clone;
    }
}
