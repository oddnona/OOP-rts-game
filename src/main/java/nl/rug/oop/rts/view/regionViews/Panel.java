package nl.rug.oop.rts.view.regionViews;

import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.nodes.Node;
import nl.rug.oop.rts.view.AnimateArmyObserver;
import nl.rug.oop.rts.view.EdgeView;
import nl.rug.oop.rts.view.GraphModelObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * panel where the graph is drawn.
 */
public class Panel extends JPanel implements GraphModelObserver, AnimateArmyObserver {

    /**
     * the background of the panel.
     */
    private Image background;

    /**
     * the image of the node to be drawn.
     */
    private Image nodeImage;
    /**
     * the list of node views.
     */
    private List<NodeView> nodeViews;
    /**
     * the list of edge view.
     */
    private List<EdgeView> edgeViews;

    /**
     * the width and height of the background image.
     */
    private int mapWidth, mapHeight;
    /**
     * the position on the panel of the background image.
     */
    private Point position;
    /**
     * the song name.
     */
    protected String songName;

    /**
     * constructor that sets up the panel.
     * @param graph the current state of the graph model.
     */
    public Panel(Graph graph) {
        redrawGraph(graph);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, position.x, position.y, null);
        for(EdgeView edge : edgeViews) {
            edge.draw(g);
        }
        for(NodeView node : nodeViews) {
            node.draw(g, nodeImage);
        }
    }

    /**
     * getter for the position.
     * @return the current position.
     */
    public Point getPosition() {
        return position;
    }

    /**
     * setter for the position.
     * @param position the new position.
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * getter for the map height.
     * @return the current map height.
     */
    public int getMapHeight() {
        return mapHeight;
    }

    /**
     * getter for the map width.
     * @return the current map width.
     */
    public int getMapWidth() {
        return mapWidth;
    }

    /**
     * setter for the background image.
     * @param background the new background image.
     */
    public void setBackground(Image background) {
        this.background = background;
    }

    /**
     * setter for the map width.
     * @param mapWidth the new map width.
     */
    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    /**
     * setter for the map height.
     * @param mapHeight the new map height.
     */
    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    /**
     * setter for the node image.
     * @param nodeImage the new node image.
     */
    public void setNodeImage(Image nodeImage) {
        this.nodeImage = nodeImage;
    }

    @Override
    public void redrawGraph(Graph graph) {
        nodeViews = new ArrayList<>();
        edgeViews = new ArrayList<>();
        for(Node node : graph.getNodes()) {
            NodeView nodeView = new NodeView(node);
            nodeViews.add(nodeView);
        }
        for(Edge edge : graph.getEdges()) {
            EdgeView edgeView = new EdgeView(edge);
            edgeViews.add(edgeView);
        }
        revalidate();
        repaint();
    }

    /**
     * getter for the list of node views.
     * @return the current list of nodes views.
     */
    public List<NodeView> getNodeViews() {
        return nodeViews;
    }

    /**
     * getter for the list of edge views.
     * @return the current list of edge views.
     */
    public List<EdgeView> getEdgeViews() {
        return edgeViews;
    }

    @Override
    public void onAnimate() {
        revalidate();
        repaint();
    }

    /**
     * getter for the song name.
     * @return the song name.
     */
    public String getSongName() {
        return songName;
    }

    /**
     * setter for the song name.
     * @param songName the song name.
     */
    public void setSongName(String songName) {
        this.songName = songName;
    }
}
