package nl.rug.oop.rts.model.events;

import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.House;
import nl.rug.oop.rts.view.Sounds;

import javax.swing.*;
import java.util.List;

/**
 * class that represents an event.
 */
public abstract class Event {
    /**
     * strings for the name of the event and the displayed message.
     */
    protected String name,  message;
    /**
     * if the event should get triggered one time only.
     * if the event was executed.
     */
    protected boolean oneTimeOnly, eventDone;
    /**
     * the house the event belongs to.
     * depending on the house of the armies and the house of the event.
     * the event behaves differently.
     */
    protected House house;
    /**
     * sounds for the popup.
     */
    private Sounds sounds;
    /**
     * sound name.
     */
    protected String soundName;

    /**
     * constructor.
     * @param house the house the event belongs to.
     */
    public Event(House house) {
        this.house = house;
        sounds = new Sounds();
    }

    /**
     * executes the event.
     * @param armies the armies affected by the event.
     */
    public abstract void doEvent(List<Army> armies);

    /**
     * creates a popup that shows what happened during the event.
     */
    protected void showPopup() {
        sounds.playSound(soundName);
        JOptionPane.showMessageDialog(null, message, name, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * getter for the one time state.
     * @return the one time state.
     */
    public boolean isOneTimeOnly() {
        return oneTimeOnly;
    }

    /**
     * getter for event done state.
     * @return the event done state.
     */
    public boolean isEventDone() {
        return eventDone;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Getter for the event's name.
     *
     * @return the name of the event
     */
    public String getName() {
        return name;
    }
}
