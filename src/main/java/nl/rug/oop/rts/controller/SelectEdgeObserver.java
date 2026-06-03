package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Edge;

/**
 * observer for when the player changes the selection of an edge.
 */
public interface SelectEdgeObserver {
    /**
     * if selected.
     * @param edge the edge.
     */
    void onSelectedEdge(Edge edge);

    /**
     * if deselected.
     * @param edge the edge.
     */
    void onUnselectedEdge(Edge edge);
}
