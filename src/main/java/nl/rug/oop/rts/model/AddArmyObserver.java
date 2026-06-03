package nl.rug.oop.rts.model;

/**
 * observer for when an army is added to the node.
 */
public interface AddArmyObserver {
    /**
     * if the army is added.
     */
    void onAddedArmy();
}
