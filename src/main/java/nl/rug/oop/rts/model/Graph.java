package nl.rug.oop.rts.model;

import nl.rug.oop.rts.json.JSONArray;
import nl.rug.oop.rts.json.JSONObject;
import nl.rug.oop.rts.model.events.Event;
import nl.rug.oop.rts.model.nodes.Node;
import nl.rug.oop.rts.view.GraphModelObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * class that represents a graph.
 * it contains nodes and has different functions for it.
 */
public class Graph implements GraphModelSubject, JSONSerializable {
    /**
     * the list of all the nodes in the graph.
     */
    private List<Node> nodes;
    /**
     * the list of all the edges in the graph.
     */
    private List<Edge> edges;
    /**
     * list of observers to update the view of the graph.
     */
    private List<GraphModelObserver> observers;

    /**
     * the previous version of the graph.
     */
    private Graph previousGraph;

    /**
     * constructor for the graph.
     */
    public Graph() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        observers = new ArrayList<>();
    }

    /**
     * adds a new node in the graph.
     * @param node the new node.
     */
    public void addNode(Node node) {
        savePreviousGraph();
        nodes.add(node);
        notifyObservers();
    }

    /**
     * disconnects all the other nodes from a node and removes it from the graph.
     * @param node the node to be removed.
     */
    public void removeNode(Node node) {
        savePreviousGraph();
        for(Edge edge : node.getEdges()) {
            edges.remove(edge);
            if(node == edge.getNode1()) {
                edge.getNode2().getEdges().remove(edge);
            } else {
                edge.getNode1().getEdges().remove(edge);
            }
        }
        node.getEdges().clear();
        nodes.remove(node);
        notifyObservers();
    }

    /**
     * connects two nodes.
     * @param name the name of the edge
     * @param node1 the first node.
     * @param node2 the second node.
     */
    public void connectNodes(String name, Node node1, Node node2) {
        savePreviousGraph();
        Edge newEdge = new Edge(name, node1, node2);
        edges.add(newEdge);
        notifyObservers();
    }

    /**
     * disconnects two nodes and removes the edge.
     * @param edge the edge to be removed.
     */
    public void disconnectNodes(Edge edge) {
        savePreviousGraph();
        edge.getNode1().getEdges().remove(edge);
        edge.getNode2().getEdges().remove(edge);
        edges.remove(edge);
        notifyObservers();
    }

    /**
     * getter for the edge models.
     * @return the list of edge models.
     */
    public List<Edge> getEdges() {
        return edges;
    }

    /**
     * getters for the node models.
     * @return the list of node models.
     */
    public List<Node> getNodes() {
        return nodes;
    }

    @Override
    public void addObserver(GraphModelObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(GraphModelObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(GraphModelObserver observer : observers) {
            observer.redrawGraph(this);
        }
    }

    /**
     * saves the previous version of the graph.
     */
    public void savePreviousGraph() {
        previousGraph = new Graph();
        Map<Node, Node> nodeMap = new HashMap<>();
        Map<Edge, Edge> edgeMap = new HashMap<>();
        for (Node node : nodes) {
            Node clonedNode = node.cloneNode();
            clonedNode.setHouse(node.getHouse());
            for(Event event : node.getEvents()) {
                clonedNode.getEvents().add(event);
            }
            nodeMap.put(node, clonedNode);
            previousGraph.getNodes().add(clonedNode);
        }
        for (Edge edge : edges) {
            Node clonedNode1 = nodeMap.get(edge.getNode1());
            Node clonedNode2 = nodeMap.get(edge.getNode2());
            Edge cloneEdge = edge.cloneEdge(clonedNode1, clonedNode2);
            clonedNode1.getEdges().add(cloneEdge);
            clonedNode2.getEdges().add(cloneEdge);
            for(Event event : edge.getEvents()) {
                cloneEdge.getEvents().add(event);
            }
            edgeMap.put(edge, cloneEdge);
            previousGraph.getEdges().add(cloneEdge);
        }
        setupArmies(nodeMap, edgeMap);
        for(GraphModelObserver observer : observers) {
            previousGraph.addObserver(observer);
        }
    }

    private void setupArmies(Map<Node, Node> nodeMap, Map<Edge, Edge> edgeMap) {
        for(Node node : nodes) {
            for(Army army : node.getArmies()) {
                Army clonedArmy = new Army(army.getSoldier(), army.getAmount());
                clonedArmy.setPreviousEdge(edgeMap.get(army.getPreviousEdge()));
                clonedArmy.setPreviousNode(nodeMap.get(army.getPreviousNode()));
                clonedArmy.setHouse(node.getHouse());
                nodeMap.get(node).getArmies().add(clonedArmy);
            }
        }
        for(Edge edge : edges) {
            for(Army army : edge.getArmies()) {
                Army clonedArmy = new Army(army.getSoldier(), army.getAmount());
                clonedArmy.setPreviousEdge(edgeMap.get(army.getPreviousEdge()));
                clonedArmy.setPreviousNode(nodeMap.get(army.getPreviousNode()));
                clonedArmy.setHouse(edge.getHouse());
                edgeMap.get(edge).getArmies().add(clonedArmy);
            }
        }
    }

    /**
     * moves the entire graph and the previous graph if it exits on the x line.
     * @param xOffset the x amount to move the graph.
     */
    public void moveGraphX(int xOffset) {
        for(Node node : nodes) {
            node.moveX(xOffset);
        }
        if(previousGraph != null) {
            for(Node node : previousGraph.getNodes()) {
                node.moveX(xOffset);
            }
        }
    }

    /**
     * moves the entire graph and the previous graph if it exists on the y line.
     * @param yOffset the y amount to move the graph.
     */
    public void moveGraphY(int yOffset) {
        for(Node node : nodes) {
            node.moveY(yOffset);
        }
        if(previousGraph != null) {
            for(Node node : previousGraph.getNodes()) {
                node.moveY(yOffset);
            }
        }
    }

    /**
     * getter for the previous graph.
     * @return the previous graph.
     */
    public Graph getPreviousGraph() {
        return previousGraph;
    }

    /**
     * getter for the list of observers.
     * @return the list of observers that listen to the graph model.
     */
    public List<GraphModelObserver> getObservers() {
        return observers;
    }

    @Override
    public Object serialize() {
        JSONObject object = new JSONObject();
        JSONArray nodesSerialized = new JSONArray();
        for (Node node : nodes) {
            if (node == null) {
                continue;
            }
            nodesSerialized.add(node.serialize());
        }
        object.put("Nodes", nodesSerialized);
        JSONArray edgesSerialized = new JSONArray();
        for (Edge edge : edges) {
            if (edge == null) {
                continue;
            }
            edgesSerialized.add(edge.serialize());
        }
        object.put("Edges", edgesSerialized);
        return object;
    }

    /**
     * erases the previous version of the graph.
     */
    public void erasePreviousGraph() {
        previousGraph = null;
    }
}
