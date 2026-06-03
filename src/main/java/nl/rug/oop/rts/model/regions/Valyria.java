package nl.rug.oop.rts.model.regions;

import nl.rug.oop.rts.model.Graph;

/**
 * old valyria region.
 */
public class Valyria extends Region{

    /**
     * constructor for the region.
     */
    public Valyria() {
        graph = new Graph();
        name = "Old Valyria";
        graph.erasePreviousGraph();
    }
}
