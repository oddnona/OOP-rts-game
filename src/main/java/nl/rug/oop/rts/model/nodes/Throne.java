package nl.rug.oop.rts.model.nodes;

import java.awt.*;

/**
 * the throne room is the biggest.
 */
public class Throne extends Node {
    /**
     * the constructor of the Node.
     *
     * @param name     the given name
     * @param position the position on the panel.
     */
    public Throne(String name, Point position) {
        super(name, position);
        range = 1000;
        full = false;
    }

    @Override
    public Node cloneNode() {
        Throne clone = new Throne(name, new Point(position));
        clone.range = range;
        clone.full = full;
        clone.house = house;
        clone.id = id;
        return clone;
    }
}
