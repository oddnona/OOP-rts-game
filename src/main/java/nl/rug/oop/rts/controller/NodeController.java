package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.nodes.Node;
import nl.rug.oop.rts.model.Player;
import nl.rug.oop.rts.view.Sounds;
import nl.rug.oop.rts.view.regionViews.NodeView;

import java.awt.*;

/**
 * class that handles the node controls.
 */
public class NodeController implements MouseObserver{
    /**
     * the node.
     */
    private Node node;
    /**
     * the view of the node.
     */
    private NodeView nodeView;
    /**
     * bool that indicates when a node is moved.
     */
    private boolean dragged;
    /**
     * the player.
     */
    private Player player;
    /**
     * sounds.
     */
    private Sounds sounds;

    /**
     * constructor for the controller.
     * @param nodeView the view of the node.
     * @param node the node.
     * @param player the player.
     */
    public NodeController(NodeView nodeView, Node node, Player player) {
        this.node = node;
        this.player = player;
        this.nodeView = nodeView;
        sounds = new Sounds();
        dragged = false;
    }

    @Override
    public void click(Point position) {
        int x = (position.x - node.getPosition().x);
        int y = (position.y - node.getPosition().y);
        if(Math.sqrt(x * x + y * y) <= (double)(node.getRange() / 2)) {
            node.setSelected(!node.isSelected());
            sounds.playSound("buttonsound.wav");
        } else {
            node.setSelected(false);
        }
    }

    @Override
    public void drag(Point position) {
        int x = (position.x - node.getPosition().x);
        int y = (position.y - node.getPosition().y);
        if(Math.sqrt(x * x + y * y) <= (double)(node.getRange() / 2) && node.isSelected()) {
            dragged = true;
        }
        if(dragged && node.getHouse() == player.getHouse()) {
            node.setPosition(position);
        }
    }

    @Override
    public void drag(int xOffset, int yOffset) {

    }

    @Override
    public void hover(Point position) {
        dragged = false;
        int x = (position.x - node.getPosition().x);
        int y = (position.y - node.getPosition().y);
        if(Math.sqrt(x * x + y * y) <= (double)(node.getRange() / 2)) {
            node.setSelectable(true);
        } else {
            node.setSelectable(false);
        }
    }

    @Override
    public void refresh() {

    }
}
