package nl.rug.oop.rts.view;

import nl.rug.oop.rts.model.Graph;

/**
 * observer that updates the state of the view of the graph.
 */
public interface GraphModelObserver {

    /**
     * updates the state of the observers when the graph is changed.
     * @param graph the new graph model.
     */
    void redrawGraph(Graph graph);
}
