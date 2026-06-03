package nl.rug.oop.rts.controller;

/**
 * Subject for the observer design pattern for the mouse manager.
 */
public interface MouseSubject {
    /**
     * adds an observer to the concrete subject.
     * @param observer the observer instance.
     */
    void addObserver(MouseObserver observer);

    /**
     * removes an observer from the concrete subject.
     * @param observer the observer instance.
     */
    void removeObserver(MouseObserver observer);

    /**
     * notifies the observers to update the panel.
     */
    void notifyObservers();
}
