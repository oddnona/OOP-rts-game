package nl.rug.oop.rts.view;

import nl.rug.oop.rts.model.AddArmyObserver;
import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.Edge;

import java.awt.*;

/**
 * class that represents the view of an edge.
 */
public class EdgeView implements AddArmyObserver {
    /**
     * the first and second army view.
     */
    private ArmyView armyView, armyView2;
    /**
     * the edge model.
     */
    private Edge edge;

    /**
     * constructor for the edge.
     * @param edge the edge.
     */
    public EdgeView(Edge edge) {
        this.edge = edge;
        onAddedArmy();
    }

    /**
     * draws the edge and the two nodes that are connected.
     * @param g the graphics object that draws on the screen
     */
    public void draw(Graphics g) {
        boolean selected = edge.isSelected();
        boolean selectable = edge.isSelectable();
        int range = edge.getRange();
        if(selected) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.LIGHT_GRAY);
        }
        Point position1 = edge.getNode1().getPosition();
        Point position2 = edge.getNode2().getPosition();
        g.drawLine(position1.x, position1.y, position2.x, position2.y);
        if(selectable) {
            g.setColor(Color.WHITE);
            g.drawOval(edge.getMiddle().x - (range / 2), edge.getMiddle().y - (range / 2), range, range);
            g.setColor(Color.LIGHT_GRAY);
            g.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 25));
            g.drawString(edge.toString(), edge.getMiddle().x - 20, edge.getMiddle().y );
        }
        if(armyView != null) {
            armyView.draw(g, this);
        }
        if(armyView2 != null) {
            armyView2.draw(g, this);
        }
    }

    /**
     * getter for the edge model.
     * @return the edge.
     */
    public Edge getEdge() {
        return edge;
    }

    @Override
    public void onAddedArmy() {
        armyView = null;
        armyView2 = null;
        for(Army army : edge.getArmies()) {
            if(edge.getHouse() != army.getHouse()) {
                armyView2 = new ArmyView(army);
            } else {
                armyView = new ArmyView(army);
            }
        }
    }
}
