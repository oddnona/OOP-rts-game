package nl.rug.oop.rts.view;

import nl.rug.oop.rts.model.Scroll;

/**
 * observer for when a new history log is added in the scroll.
 */
public interface AddLogObserver {

    /**
     * updates the state of the observer.
     * @param scroll the scroll with the logs.
     */
    void onAddLog(Scroll scroll);
}
