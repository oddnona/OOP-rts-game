package nl.rug.oop.rts.view;

/**
 * observer that does something when the scroll is opened or closed.
 */
public interface OpenScrollObserver {
    /**
     * does something when the scroll is opened or closed.
     */
    void update();
}
