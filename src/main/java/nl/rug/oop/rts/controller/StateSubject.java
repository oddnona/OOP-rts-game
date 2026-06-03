package nl.rug.oop.rts.controller;

/**
 * subject for the state observers that notifies them to update their states.
 */
public interface StateSubject {
    /**
     * adds an observer.
     * @param observer the new observer.
     */
    void addObserver(StateObserver observer);

    /**
     * removes an observer from the list.
     * @param observer the discarded observer.
     */
    void removeObserver(StateObserver observer);
}
