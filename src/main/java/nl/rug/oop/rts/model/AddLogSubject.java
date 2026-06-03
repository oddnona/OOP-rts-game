package nl.rug.oop.rts.model;

import nl.rug.oop.rts.view.AddLogObserver;

/**
 * subject that notifies all observers when a log is added to the scroll.
 */
public interface AddLogSubject {
    /**
     * add the observer.
     * @param observer the observer.
     */
    void addObserver(AddLogObserver observer);

    /**
     * removes the observer.
     * @param observer the observer.
     */
    void removeObserver(AddLogObserver observer);

    /**
     * notifies the observers.
     */
    void notifyObservers();
}
