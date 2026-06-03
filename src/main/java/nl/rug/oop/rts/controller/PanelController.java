package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Game;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.view.EdgeView;
import nl.rug.oop.rts.view.GraphModelObserver;
import nl.rug.oop.rts.view.regionViews.NodeView;
import nl.rug.oop.rts.view.regionViews.Panel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * controller for the panel.
 */
public class PanelController implements MouseObserver, GraphModelObserver {
    /**
     * the panel.
     */
    private Panel panel;
    /**
     * list of node controllers.
     */
    private List<NodeController> nodeControllers;
    /**
     * list of edge controllers.
     */
    private List<EdgeController> edgeControllers;
    /**
     * the game.
     */
    private Game game;

    /**
     * constructor for the panel.
     * @param panel the panel view.
     * @param game the game model.
     */
    public PanelController(Panel panel, Game game) {
        this.panel = panel;
        this.game = game;
        Graph graph = game.getPlayer().getCurrentRegion().getGraph();
        graph.addObserver(panel);
        redrawGraph(graph);
    }

    @Override
    public void click(Point position) {

    }

    @Override
    public void drag(Point position) {

    }

    @Override
    public void drag(int xOffset, int yOffset) {
        int x = panel.getPosition().x + xOffset;
        int y = panel.getPosition().y + yOffset;
        if (x < 0 && x + panel.getMapWidth() > panel.getWidth()) {
            panel.setPosition(new Point(x, panel.getPosition().y));
            Graph graph = game.getPlayer().getCurrentRegion().getGraph();
            graph.moveGraphX(xOffset);
        }
        if(y < 0 && y + panel.getMapHeight() > panel.getHeight()) {
            panel.setPosition(new Point(panel.getPosition().x, y));
            Graph graph = game.getPlayer().getCurrentRegion().getGraph();
            graph.moveGraphY(yOffset);
        }
    }

    @Override
    public void hover(Point position) {

    }

    @Override
    public void refresh() {
        panel.repaint();
    }

    /**
     * getter for the list of node controllers.
     * @return the node controller list.
     */
    public List<NodeController> getNodeControllers() {
        return nodeControllers;
    }

    /**
     * getter for the edge controllers.
     * @return the edge controller list.
     */
    public List<EdgeController> getEdgeControllers() {
        return edgeControllers;
    }

    @Override
    public void redrawGraph(Graph graph) {
        nodeControllers = new ArrayList<>();
        edgeControllers = new ArrayList<>();
        for(NodeView nodeView : panel.getNodeViews()) {
            nodeControllers.add(new NodeController(nodeView, nodeView.getNode(), game.getPlayer()));
        }
        for(EdgeView edgeView : panel.getEdgeViews()) {
            edgeControllers.add(new EdgeController(edgeView, edgeView.getEdge()));
        }
    }
}
