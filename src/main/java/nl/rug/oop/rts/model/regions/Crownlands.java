package nl.rug.oop.rts.model.regions;

import nl.rug.oop.rts.model.Graph;

/**
 * crownlands region.
 */
public class Crownlands extends Region{

    /**
     * constructor for the region.
     */
    public Crownlands() {
        graph = new Graph();
        name = "Crownlands";
        graph.erasePreviousGraph();
    }
}
