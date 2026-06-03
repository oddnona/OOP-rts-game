package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.nodes.Node;

/**
 * observer for when the player changes the selection of a node.
 */
public interface SelectNodeObserver {
    /**
     * if selected.
     * @param node the node.
     */
    void onSelect(Node node);

    /**
     * if deselected.
     * @param node the node.
     */
    void onUnSelect(Node node);
}
