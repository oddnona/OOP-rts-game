package nl.rug.oop.rts.model;

import nl.rug.oop.rts.model.regions.Region;
import nl.rug.oop.rts.model.events.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * class that represents the player of the game.
 */
public class Player {
    /**
     * the house the player belongs to.
     */
    private House house;
    /**
     * the list of all the regions conquered by the player.
     */
    private List<Region> territories;
    /**
     * the list of all the armies the player currently has.
     */
    private List<Army> armies;
    /**
     * the list of all the previous armies before an action.
     */
    private List<Army> previousArmies;
    /**
     * the list of all the previous events.
     */
    private List<Event> events;
    /**
     * the list of the previous events.
     */
    private List<Event> previousEvents;
    /**
     * the current region.
     */
    private Region currentRegion;

    /**
     * constructor for the player.
     * @param house the house the player chooses at the beginning of the game.
     */
    public Player(House house) {
        this.house = house;
        territories = new ArrayList<>();
        armies = new ArrayList<>();
        events = new ArrayList<>();
    }

    /**
     * setter for the region.
     * @param region the region.
     */
    public void setRegion(Region region) {
        currentRegion = region;
    }

    /**
     * getter for the list of armies.
     * @return the list of armies.
     */
    public List<Army> getArmies() {
        return armies;
    }

    /**
     * adds an army to the list.
     * @param army the new army.
     */
    public void addArmy(Army army) {
        boolean newType = true;
        for(Army a : armies) {
            if(a.getSoldier().getArmyType() == army.getSoldier().getArmyType() ) {
                a.setAmount(a.getAmount() + army.getAmount());
                newType = false;
            }
        }
        if(newType) {
            armies.add(army);
        }
    }

    /**
     * getter for the list of territories.
     * @return the current list of territories.
     */
    public List<Region> getTerritories() {
        return territories;
    }

    /**
     * getter for the player's house.
     * @return the player's house.
     */
    public House getHouse() {
        return house;
    }

    /**
     * getter for the current region.
     * @return the current region.
     */
    public Region getCurrentRegion() {
        return currentRegion;
    }

    /**
     * removes armies that have 0 soldiers from the list.
     */
    public void removeEmptyArmies() {
        List<Army> armiesToRemove = new ArrayList<>(armies);
        for(Army a : armiesToRemove) {
            if(a.getAmount() == 0) {
                armies.remove(a);
            }
        }
    }

    /**
     * saves the state of the player.
     */
    public void savePlayer() {
        previousArmies = new ArrayList<>();
        for (Army army : armies) {
            Army clonedArmy = new Army(army.getSoldier(), army.getAmount());
            clonedArmy.setHouse(army.getHouse());
            previousArmies.add(clonedArmy);
        }
        previousEvents = new ArrayList<>();
        previousEvents.addAll(events);
    }

    /**
     * getter for the previous army list.
     * @return the previous list of armies.
     */
    public List<Army> getPreviousArmies() {
        return previousArmies;
    }

    /**
     * getter for the events list.
     * @return the events list.
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * getter for the previous events.
     * @return the previous events.
     */
    public List<Event> getPreviousEvents() {
        return previousEvents;
    }

    /**
     * erases the previous version of the player.
     */
    public void eraseHistory() {
        previousArmies.clear();
        previousEvents.clear();
    }
}