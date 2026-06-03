package nl.rug.oop.rts.view.regionViews;

import nl.rug.oop.rts.model.AddArmyObserver;
import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.nodes.Node;
import nl.rug.oop.rts.view.ArmyView;

import java.awt.*;

/**
 * class that represents the view of a node.
 */
public class NodeView implements AddArmyObserver {

    /**
     * the node model.
     */
    private Node node;
    /**
     * the first and second army view.
     */
    private ArmyView armyView, armyView2;

    /**
     * constructor for the node view.
     * @param node the model of the node
     */
    public NodeView(Node node) {
        this.node = node;
        onAddedArmy();
    }
    
    /**
     * draws the edges of the node and the node itself.
     * @param g the graphics object that draws on the screen.
     * @param image the image of the node to be drawn.
     */
    public void draw(Graphics g, Image image) {
        g.setColor(Color.lightGray);
        Point position = node.getPosition();
        int range = node.getRange();
        boolean selected = node.isSelected();
        boolean selectable = node.isSelectable();
        if(!node.isFull()) {
            g.drawOval(position.x - (range / 2), position.y - (range / 2), range, range);
            if(selected) {
                g.setColor(new Color(0, 0, 0, 89));
                g.drawOval(position.x - (range / 2), position.y - (range / 2), range, range);
            }
        } else {
            g.fillOval(position.x - (range / 2), position.y - (range / 2), range, range);
            if(selected) {
                g.setColor(new Color(0, 0, 0, 89));
                g.fillOval(position.x - (range / 2), position.y - (range / 2), range, range);
            }
        }
        if(selectable) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 25));
            g.drawString(node.toString(), position.x - 20, position.y);
        }
        if(armyView != null) {
            armyView.draw(g, this);
        }
        if(armyView2 != null) {
            armyView2.draw(g, this);
        }
    }

    /**
     * getter for the node.
     * @return the current state of the node.
     */
    public Node getNode() {
        return node;
    }

    @Override
    public void onAddedArmy() {
        armyView = null;
        armyView2 = null;
        for(Army army : node.getArmies()) {
            if(node.getHouse() != army.getHouse()) {
                armyView2 = new ArmyView(army);
            } else {
                armyView = new ArmyView(army);
            }
        }
    }
}
