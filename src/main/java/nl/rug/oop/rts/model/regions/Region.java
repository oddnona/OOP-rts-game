package nl.rug.oop.rts.model.regions;

import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.House;
import nl.rug.oop.rts.model.events.Event;
import nl.rug.oop.rts.model.JSONSerializable;
import nl.rug.oop.rts.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * class that represents a region that has a battlefield.
 */
public abstract class Region implements JSONSerializable {
    /**
     * the graph that represents the battlefield.
     */
    protected Graph graph;
    /**
     * the list of armies the region has.
     */
    protected List<Army> armies;
    /**
     * the list of events the region has.
     */
    protected List<Event> events;
    /**
     * the region name.
     */
    protected String name;
    /**
     * the house.
     */
    protected House house;

    /**
     * constructor for the region model.
     *
     */
    public Region() {
        graph = new Graph();
        armies = new ArrayList<>();
        events = new ArrayList<>();
    }

    /**
     * getter for the name.
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * getter for the graph.
     * @return the current state of the graph.
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * setter for the graph.
     * @param graph the new graph.
     */
    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    /**
     * getter for the current list of armies.
     * @return the current list of armies.
     */
    public List<Army> getArmies() {
        return armies;
    }

    /**
     * setter for the house.
     * @param house the house.
     */
    public void setHouse(House house) {
        this.house = house;
    }

    /**
     * getter for the house.
     * @return the house.
     */
    public House getHouse() {
        return house;
    }

    /**
     * getter for the list of events.
     * @return the list of events.
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * serializes the region into a JSON object.
     * @return the serialized region.
     */
    @Override
    public Object serialize() {
        JSONObject object = new JSONObject();

        object.put("Name", name);

        object.putAll((JSONObject) graph.serialize());

        return object;
    }
}