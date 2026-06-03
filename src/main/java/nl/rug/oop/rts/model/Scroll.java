package nl.rug.oop.rts.model;

import nl.rug.oop.rts.view.AddLogObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * class for the scroll that shows the history log.
 */
public class Scroll implements AddLogSubject {
    /**
     * list of the written logs.
     */
    private List<String> logs;
    /**
     * observers for the logs.
     */
    private List<AddLogObserver> observers;

    /**
     * constructor for the scroll class.
     */
    public Scroll() {
        logs = new ArrayList<>();
        logs.add("The last targaryen king has been slayed, " +
                "and its usurper has died of sickness after 15 years of ruling. " +
                "The throne is now a matter of conquer.");
        observers = new ArrayList<>();
    }

    /**
     * getter for the list of logs.
     * @return the list of logs.
     */
    public List<String> getLogs() {
        return logs;
    }

    /**
     * adds a log to the list.
     * @param log the log.
     */
    public void addLog(String log) {
        logs.add(log);
        notifyObservers();
    }

    @Override
    public void addObserver(AddLogObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(AddLogObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(AddLogObserver observer : observers) {
            observer.onAddLog(this);
        }
    }
}
