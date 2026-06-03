package nl.rug.oop.rts.model.nodes;

import nl.rug.oop.rts.controller.SelectNodeObserver;
import nl.rug.oop.rts.model.*;
import nl.rug.oop.rts.model.events.Event;
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
 * Class that represents a node that can connect to other nodes using Edges.
 */
public abstract class Node implements ActionListener, JSONSerializable {
    /**
     * a static variable that gives the next id available.
     */
    protected static int ids;
    /**
     * the node's id.
     */
    protected int id;
    /**
     * the name of the node.
     */
    protected String name;
    /**
     * the list of all the connected edges.
     */
    protected List<Edge> edges;
    /**
     * a list of all the types of armies on the node.
     */
    protected List<Army> armies;
    /**
     * the position of the node on the panel.
     */
    protected Point position;
    /**
     * node size.
     */
    protected int range;
    /**
     * if you want the node to be drawn full.
     */
    protected boolean full;
    /**
     * if the node is selected.
     */
    protected boolean selected;
    /**
     * boolean that tells if the node can be selected.
     */
    protected boolean selectable;
    /**
     * list of obervers for a node.
     */
    protected List<SelectNodeObserver> selectNodeObservers;
    /**
     * list of army observers.
     */
    protected List<AddArmyObserver> armyObservers;
    /**
     * timer to update the position on the edge of the army.
     */
    protected Timer timer;
    /**
     * the house.
     */
    protected House house;
    /**
     * the list of observers when the animation happens.
     */
    protected List<AnimateArmyObserver> observers;
    /**
     * the list of events on the node.
     */
    private List<nl.rug.oop.rts.model.events.Event> events;

    /**
     * the constructor of the Node.
     * @param name the given name
     * @param position the position on the panel.
     */
    public Node(String name, Point position) {
        this.name = name;
        this.position = position;
        timer = new Timer(40, this);
        selectNodeObservers = new ArrayList<>();
        armyObservers = new ArrayList<>();
        events = new ArrayList<>();
        observers = new ArrayList<>();
        range = 50;
        edges = new ArrayList<>();
        armies = new ArrayList<>();
        id = ids;
        ids++;
    }

    /**
     * adds an army on the node.
     * @param army the army added.
     */
    public void deployTroops(Army army) {
        armies.add(army);
        army.setPreviousNode(this);
        army.setPreviousEdge(null);
        for(AddArmyObserver observer : armyObservers) {
            observer.onAddedArmy();
        }
    }

    /**
     * removes an army from the node.
     * @param army the army removed.
     */
    public void removeTroops(Army army) {
        armies.remove(army);
        for(AddArmyObserver observer : armyObservers) {
            observer.onAddedArmy();
        }
    }

    /**
     * redirects an army to head to a neighbour edge.
     * @param army the army selected.
     * @param edge the edge that the army will go on.
     */
    public void commandArmy(Army army, Edge edge) {
        removeTroops(army);
        edge.addArmy(army);
        army.setPositionOnTheEdge(0);
    }

    /**
     * getter for the id.
     * @return the id.
     */
    public int getId() {
        return id;
    }

    /**
     * getter for the current name.
     * @return the current name.
     */
    public String getName() {
        return name;
    }

    /**
     * getter for the edges.
     * @return the edges.
     */
    public List<Edge> getEdges() {
        return edges;
    }

    /**
     * setter for the name of the node.
     * @param name the new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setter for the position.
     * @param position the new position.
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * getter for the position.
     * @return the current position.
     */
    public Point getPosition() {
        return position;
    }

    /**
     * getter for the selectable.
     * @return the current selectale state.
     */
    public boolean isSelectable() {
        return selectable;
    }

    /**
     * setter for the selectable.
     * @param selectable the new selectable state.
     */
    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    /**
     * getter for the list of armies.
     * @return the current list of armies.
     */
    public List<Army> getArmies() {
        return armies;
    }

    /**
     * setter for the selected state.
     * @param selected the selected state.
     */
    public void setSelected(boolean selected) {
        if(selected && !this.selected) {
            this.selected = selected;
            for(SelectNodeObserver observer : selectNodeObservers) {
                observer.onSelect(this);
            }
        } else if(!selected && this.selected){
            this.selected = selected;
            for(SelectNodeObserver observer : selectNodeObservers) {
                observer.onUnSelect(this);
            }
        } else {
            this.selected = selected;
        }
    }

    /**
     * getter for the selected state.
     * @return the selected state.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * getter for the node range.
     * @return the range.
     */
    public int getRange() {
        return range;
    }

    /**
     * moves the node view along with the map in the x direction.
     * @param xOffset the x amount moved by the mouse.
     */
    public void moveX(int xOffset) {
        int x = position.x + xOffset;
        setPosition(new Point(x, position.y));
    }

    /**
     * moves the node view along with the map in the y direction.
     * @param yOffset the y amount moved by the mouse.
     */
    public void moveY(int yOffset) {
        int y = position.y + yOffset;
        setPosition(new Point(position.x, y));
    }

    /**
     * adds an observer to the selected node.
     * @param observer the observer.
     */
    public void addSelectObserver(SelectNodeObserver observer) {
        selectNodeObservers.add(observer);
    }

    /**
     * removes an observer from the selected node.
     * @param observer the observer.
     */
    public void removeSelectObserver(SelectNodeObserver observer) {
        selectNodeObservers.remove(observer);
    }

    /**
     * getter for list of observers.
     * @return the list of observers.
     */
    public List<SelectNodeObserver> getSelectNodeObservers() {
        return selectNodeObservers;
    }

    /**
     * adds an army observer.
     * @param observer the observer.
     */
    public void addArmyObserver(AddArmyObserver observer) {
        armyObservers.add(observer);
    }

    /**
     * calculates the distance between two points.
     * @param start the fist point.
     * @param end the second point.
     * @return the distance.
     */
    private int getLength(Point start, Point end) {
        int x = start.x - end.x;
        int y = start.y - end.y;
        return (int)Math.sqrt((x * x) + (y * y));
    }

    /**
     * is the node full or not.
     * @return the full node state.
     */
    public boolean isFull() {
        return full;
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
            for(Army army : getArmies()) {
                if(army.isMoving()) {
                    running = true;
                }
                if(army.getHouse() != house1) {
                    battle = true;
                }
            }
        }
        if(running) {
            for (Army army : armies) {
                if (army.isMoving()) {
                    int positionOnEdge = army.getPositionOnTheEdge();
                    if (army.getPreviousEdge() != null &&
                            getLength(position, army.getPreviousEdge().getMiddle()) - 40 > positionOnEdge) {
                        army.setPositionOnTheEdge(army.getPositionOnTheEdge() + 10);
                        army.animate();
                    } else {
                        if(!battle) {
                            army.setMoving(false);
                        } else {
                            army.setPositionOnTheEdge(army.getPositionOnTheEdge() - 10);
                            army.animate();
                        }
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
     * to clone the object.
     * @return the cloned object.
     */
    public abstract Node cloneNode();

    /**
     * getter for the timer.
     * @return the timer.
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * adds an animation observer.
     * @param observer the animation observer.
     */
    public void addObserver(AnimateArmyObserver observer) {
        observers.add(observer);
    }

    @Override
    public Object serialize() {
        JSONObject object = new JSONObject();

        object.put("Id", id);
        object.put("Name", name);

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

        JSONObject positionSerialized = new JSONObject();
        if (position != null) {
            positionSerialized.put("x", position.x);
            positionSerialized.put("y", position.y);
        } else {
            positionSerialized = null;
        }
        object.put("Position", positionSerialized);
        return object;
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
}