package nl.rug.oop.rts.view;

/**
 * subject for when the scroll is opened or closed, it notifies the other observers.
 */
public interface OpenScrollSubject {
    /**
     * adds an observer to the subject.
     * @param observer the new observer.
     */
    void addObserver(OpenScrollObserver observer);

    /**
     * removes an observer from the subject.
     * @param observer the removed observer.
     */
    void removeObserver(OpenScrollObserver observer);

    /**
     * notifies all the observers when the scroll is opened or closed.
     */
    void notifyObservers();
}
