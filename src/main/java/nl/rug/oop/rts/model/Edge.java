package nl.rug.oop.rts.model;

import nl.rug.oop.rts.controller.SelectEdgeObserver;
import nl.rug.oop.rts.model.events.Event;
import nl.rug.oop.rts.model.nodes.Node;
import nl.rug.oop.rts.view.AnimateArmyObserver;
import nl.rug.oop.rts.json.JSONArray;
import nl.rug.oop.rts.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * class that represents an edge of a graph, it can connect 2 nodes.
 */
public class Edge implements ActionListener, JSONSerializable {
    /**
     * a static variable that gives the next id available.
     */
    private static int ids;
    /**
     * a unique id.
     */
    private int id;
    /**
     * the name of the edge.
     */
    private String name;
    /**
     * the 2 nodes that the edge connects.
     */
    private Node node1, node2;
    /**
     * the list of all types of armies currently on the edge.
     */
    private List<Army> armies;
    /**
     * list of observers for selected edges.
     */
    private List<SelectEdgeObserver> selectObservers;
    /**
     * if an edge is selectable.
     */
    private boolean selectable;
    /**
     * if an edge is selected.
     */
    private boolean selected;
    /**
     * selectable area on the edge.
     */
    private int range;
    /**
     * list of army observers.
     */
    private List<AddArmyObserver> armyObservers;
    /**
     * timer to update the position on the edge and animate the view.
     */
    private Timer timer;
    /**
     * the house the edge belongs to.
     */
    private House house;
    /**
     * list of observers when the animation is happening.
     */
    private List<AnimateArmyObserver> observers;
    /**
     * list of events on the edge.
     */
    private List<nl.rug.oop.rts.model.events.Event> events;

    /**
     * constructor for the new edge.
     * @param name the given name.
     * @param node1 the first node that is connected.
     * @param node2 the second node that is connected.
     */
    public Edge(String name, Node node1, Node node2) {
        this.name = name;
        timer = new Timer(40, this);
        selectObservers = new ArrayList<>();
        armyObservers = new ArrayList<>();
        observers = new ArrayList<>();
        range = 50;
        id = ids;
        ids++;
        if(node1 != null && node2 != null) {
            this.node1 = node1;
            this.node2 = node2;
            this.node1.getEdges().add(this);
            this.node2.getEdges().add(this);
        }
        armies = new ArrayList<>();
        events = new ArrayList<>();
    }

    /**
     * adds an army on the edge.
     * @param army the army added.
     */
    public void addArmy(Army army) {
        armies.add(army);
    }

    /**
     * removes an army from the edge.
     * @param army the army removed.
     */
    public void removeArmy(Army army) {
        armies.remove(army);
    }

    /**
     * moves the army from the edge to the node selected.
     * @param army the army in control.
     * @param node the destination.
     */
    public void commandArmy(Army army, Node node) {
        removeArmy(army);
        army.setPositionOnTheEdge(0);
        if(node1 == node) {
            node1.getArmies().add(army);
        } else {
            node2.getArmies().add(army);
        }
    }

    /**
     * adds an army observer.
     * @param observer the observer.
     */
    public void addArmyObserver(AddArmyObserver observer) {
        armyObservers.add(observer);
    }

    /**
     * getter for the first node.
     * @return the first node.
     */
    public Node getNode1() {
        return node1;
    }

    /**
     * getter for the second node.
     * @return the second node.
     */
    public Node getNode2() {
        return node2;
    }

    /**
     * getter for the name of the edge.
     * @return the name of the edge.
     */
    public String getName() {
        return name;
    }

    /**
     * setter for the name of the edge.
     * @param name the name of the edge.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for the list of armies.
     * @return the current list of armies.
     */
    public List<Army> getArmies() {
        return armies;
    }

    /**
     * getter for the length of the edge.
     * @return the length of the edge.
     */
    public int getLength() {
        int x = node1.getPosition().x - node2.getPosition().x;
        int y = node1.getPosition().y - node2.getPosition().y;
        return (int)Math.sqrt((x * x) + (y * y));
    }

    /**
     * getter for the range.
     * @return the range.
     */
    public int getRange() {
        return range;
    }

    /**
     * getter for the selectable state.
     * @return selectable.
     */
    public boolean isSelectable() {
        return selectable;
    }

    /**
     * setter for the selectable state.
     * @param selectable the selectable state.
     */
    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    /**
     * getter for selected state.
     * @return the selected state.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * setter for the selected state.
     * @param selected the selected state.
     */
    public void setSelected(boolean selected) {
        if(selected && !this.selected) {
            this.selected = selected;
            for(SelectEdgeObserver observer : selectObservers) {
                observer.onSelectedEdge(this);
            }
        } else if(!selected && this.selected){
            this.selected = selected;
            for(SelectEdgeObserver observer : selectObservers) {
                observer.onUnselectedEdge(this);
            }
        } else {
            this.selected = selected;
        }
    }

    /**
     * getter for the middle of the edge.
     * @return coordinates of the middle point.
     */
    public Point getMiddle() {
        Point position1 = node1.getPosition();
        Point position2 = node2.getPosition();
        int x = (position1.x + position2.x) / 2;
        int y = (position1.y + position2.y) / 2;
        return new Point(x, y);
    }

    /**
     * adds an observer to the edge.
     * @param observer the observer.
     */
    public void addSelectObserver(SelectEdgeObserver observer) {
        selectObservers.add(observer);
    }

    /**
     * getter for the observers of an edge.
     * @return the list of observers.
     */
    public List<SelectEdgeObserver> getSelectObservers() {
        return selectObservers;
    }

    @Override
    public String toString() {
        return name + " " + id;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean running = false;
        boolean battle = false;
        if(!armies.isEmpty()) {
            House house1 = armies.get(0).getHouse();
            for(Army army : armies) {
                if(army.getHouse() != house1) {
                    battle = true;
                }
                if(army.isMoving()) {
                    running = true;
                }
            }
        }
        if(running) {
            for(Army army : armies) {
                if((getLength() / 2) - 40 > army.getPositionOnTheEdge()) {
                    army.setPositionOnTheEdge(army.getPositionOnTheEdge() + 10);
                    army.animate();
                } else {
                    if (!battle) {
                        army.setMoving(false);
                    } else {
                        army.setPositionOnTheEdge(army.getPositionOnTheEdge() - 10);
                        army.animate();
                    }
                }
            }
            for(AnimateArmyObserver observer : observers) {
                observer.onAnimate();
            }
        } else {
            timer.stop();
        }
    }

    /**
     * getter for the timer.
     * @return the timer.
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * getter for the house.
     * @param house the new house.
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
     * adds an animation observer.
     * @param observer the observer.
     */
    public void addObserver(AnimateArmyObserver observer) {
        observers.add(observer);
    }

    @Override
    public Object serialize() {
        JSONObject object = new JSONObject();

        object.put("Id", id);
        object.put("Name", name);
        object.put("Node1", node1.getId());
        object.put("Node2", node2.getId());

        JSONArray armiesSerialized = new JSONArray();
        for (Army army : armies) {
            if (army == null) {
                continue;
            }
            armiesSerialized.add(army.serialize());
        }
        object.put("Armies", armiesSerialized);

        JSONArray eventsSerialized = new JSONArray();
        for (Event event : events) {
            eventsSerialized.add(event.getName());
        }
        object.put("Events", eventsSerialized);

        return object;
    }

    /**
     * clones the edge.
     * @return the cloned state of the edge.
     */
    public Edge cloneEdge(Node clonedNode1, Node clonedNode2) {
        Edge clone = new Edge(name, null, null);
        clone.setNode1(clonedNode1);
        clone.setNode2(clonedNode2);
        clone.house = house;
        clone.id = id;
        return clone;
    }

    /**
     * add an event to the node.
     * @param event the event added.
     */
    public void addEvent(nl.rug.oop.rts.model.events.Event event) {
        events.add(event);
    }

    /**
     * removes an event from the node.
     * @param event the removed event.
     */
    public void removeEvent(nl.rug.oop.rts.model.events.Event event) {
        events.remove(event);
    }

    /**
     * getter for the current list of events.
     * @return the currnet list of events.
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * setter for the first node.
     * @param node1 the new first node.
     */
    protected void setNode1(Node node1) {
        this.node1 = node1;
    }

    /**
     * setter for the second node.
     * @param node2 the new second node.
     */
    protected void setNode2(Node node2) {
        this.node2 = node2;
    }
}