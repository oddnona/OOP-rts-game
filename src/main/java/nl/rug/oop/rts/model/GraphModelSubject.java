package nl.rug.oop.rts.model;

import nl.rug.oop.rts.view.GraphModelObserver;

/**
 * subject that notifies the observers to update their state.
 */
public interface GraphModelSubject {
    /**
     * add an observer to the list of observers.
     * @param observer the new observer.
     */
    void addObserver(GraphModelObserver observer);

    /**
     * removes an observer from the list.
     * @param observer the discarded observer.
     */
    void removeObserver(GraphModelObserver observer);

    /**
     * notifies the observers.
     */
    void notifyObservers();
}
