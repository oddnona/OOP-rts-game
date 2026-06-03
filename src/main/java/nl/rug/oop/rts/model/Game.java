package nl.rug.oop.rts.model;

import nl.rug.oop.rts.controller.StateObserver;
import nl.rug.oop.rts.controller.StateSubject;
import nl.rug.oop.rts.model.events.Event;
import nl.rug.oop.rts.model.nodes.Node;
import nl.rug.oop.rts.model.regions.Region;

import java.util.ArrayList;
import java.util.List;

/**
 * class that resolves the model of the game.
 */
public class Game implements StateSubject {
    /**
     * the instance of the player.
     */
    private Player player;
    /**
     * the list of observers.
     */
    private List<StateObserver> observers;
    /**
     * the map of the game.
     */
    private Map map;
    /**
     * list of army observers.
     */
    private List<AddArmyObserver> addArmyObservers;
    /**
     * the simulation class that simulates a step.
     */
    private Simulation simulation;

    /**
     * constructor for the game.
     */
    public Game() {
        observers = new ArrayList<>();
        addArmyObservers = new ArrayList<>();
    }

    /**
     * creates a new game.
     */
    public void createNewGame() {
        map = new Map();
        for(StateObserver observer : observers) {
            observer.setNewHouse(this);
        }
    }

    /**
     * undo the player's last action.
     */
    public void undo() {
        player.getArmies().clear();
        for (Army army : player.getPreviousArmies()) {
            player.getArmies().add(army);
        }
        player.getEvents().clear();
        for (Event event : player.getPreviousEvents()) {
            player.getEvents().add(event);
        }
        player.eraseHistory();
        for(Node node : player.getCurrentRegion().getGraph().getNodes()) {
            node.setSelected(false);
        }
        for(Edge edge : player.getCurrentRegion().getGraph().getEdges()) {
            edge.setSelected(false);
        }
        Graph graph = player.getCurrentRegion().getGraph();
        if (graph.getPreviousGraph() == null) {
            System.err.println("ERROR: undoToPreviousGraph() returned null");
        } else {
            player.getCurrentRegion().setGraph(graph.getPreviousGraph());
        }
        player.getCurrentRegion().getGraph().notifyObservers();
    }

    /**
     * simulates a phase.
     */
    public void simulateStep() {
        simulation.simulate();
    }

    /**
     * creates the instance of the player.
     * @param house the house/faction the player decided to belong to.
     */
    public void createPlayer(House house) {
        player = new Player(house);
        Region region = map.getRegionMap().get(house);
        player.getTerritories().add(region);
        for(Army army : region.getArmies()) {
            player.addArmy(army);
        }
        for(Event event : region.getEvents()) {
            player.getEvents().add(event);
        }
        simulation = new Simulation(this);
        for(StateObserver observer : observers) {
            observer.startGame(this);
        }
    }

    /**
     * setter for the current region.
     * @param region the current region.
     */
    public void setCurrentRegion(Region region) {
        player.setRegion(region);
        map.getScroll().addLog("You visited " + region.getName());
        for(StateObserver observer : observers) {
            observer.enterRegion(this);
        }
    }

    /**
     * returns to the main map panel.
     */
    public void returnToMap() {
        for(StateObserver observer : observers) {
            observer.enterMainMap(this);
        }
    }

    /**
     * getter for the map.
     * @return the map.
     */
    public Map getMap() {
        return map;
    }

    /**
     * getter for the player.
     * @return the current state of the player.
     */
    public Player getPlayer() {
        return player;
    }

    @Override
    public void addObserver(StateObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(StateObserver observer) {
        observers.remove(observer);
    }

    /**
     * adds an army observer to the game.
     * @param observer the observer.
     */
    public void addArmyObserver(AddArmyObserver observer) {
        addArmyObservers.add(observer);
    }

    /**
     * getter for the list of observers when an army is added.
     * @return the list of observers,
     */
    public List<AddArmyObserver> getAddArmyObservers() {
        return addArmyObservers;
    }
}