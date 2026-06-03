package nl.rug.oop.rts.view;

import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.nodes.Node;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicMenuBarUI;
import java.awt.*;

/**
 * class that creates a menu bar.
 */
public class MenuBar extends JMenuBar {
    /**
     * button for adding a new node.
     */
    private JMenuItem addNode;
    /**
     * button for removing a node.
     */
    private JMenuItem removeNode;
    /**
     * button for connecting two nodes.
     */
    private JMenuItem connectNodes;
    /**
     * button for disconnecting two nodes.
     */
    private JMenuItem disconnectNodes;
    /**
     * button to undo a player mistake.
     */
    private JMenuItem undo;
    /**
     * button that sends back to the main map panel.
     */
    private JMenuItem map;
    /**
     * button that simulates a phase.
     */
    private JMenuItem simulateStep;
    /**
     * button that saves the simulation state.
     */
    private JMenuItem save;

    /**
     * constructor that sets up the menu bar.
     */
    public MenuBar() {
        setOpaque(true);
        setBackground(new Color(0, 0, 0));
        setUI(new BasicMenuBarUI());
        setBorder(BorderFactory.createEmptyBorder());
        addNode = makeItem("Place camp");
        addNode.setEnabled(false);
        removeNode = makeItem("Delete camp");
        removeNode.setEnabled(false);
        connectNodes = makeItem("Connect camps");
        connectNodes.setEnabled(false);
        disconnectNodes = makeItem("Disconnect camps");
        disconnectNodes.setEnabled(false);
        map = makeItem("Return to map");
        map.setEnabled(false);
        simulateStep = makeItem("Simulate step");
        simulateStep.setEnabled(false);
        undo = makeItem("Undo");
        undo.setEnabled(false);
        save = new JMenuItem("Save");
        save.setEnabled(true);
        add(addNode);
        add(removeNode);
        add(connectNodes);
        add(disconnectNodes);
        add(simulateStep);
        add(undo);
        add(save);
        add(map);

    }

    /**
     * buttons look.
     * @param text the button text.
     * @return the button.
     */
    private JMenuItem makeItem(String text) {
        JMenuItem item = new JMenuItem(text);
        item.setEnabled(false);
        item.setOpaque(true);
        item.setBackground(new Color(0, 0, 0, 255));
        item.setForeground(Color.WHITE);
        item.setBorder(new EmptyBorder(0, 40, 0, 110));
        return item;
    }

    /**
     * getter for add node button.
     * @return the add node button.
     */
    public JMenuItem getAddNode() {
        return addNode;
    }

    /**
     * getter for the remove node button.
     * @return the remove node button.
     */
    public JMenuItem getRemoveNode() {
        return removeNode;
    }

    /**
     * getter for the connect nodes button.
     * @return the connect nodes button.
     */
    public JMenuItem getConnectNodes() {
        return connectNodes;
    }

    /**
     * getter for the disconnect nodes button.
     * @return the disconnect nodes button.
     */
    public JMenuItem getDisconnectNodes() {
        return disconnectNodes;
    }

    /**
     * getter for the return to map button.
     * @return the map button
     */
    public JMenuItem getMap() {
        return map;
    }

    /**
     * getter for the simulate set button.
     * @return the simulate button.
     */
    public JMenuItem getSimulateStep() {
        return simulateStep;
    }

    /**
     * getter for the save button.
     * @return the save button.
     */
    public JMenuItem getSave() {
        return save;
    }

    /**
     * getter for the undo button.
     * @return the undo button.
     */
    public JMenuItem getUndo() {
        return undo;
    }

    /**
     * when an edge is selected.
     * @param edge the selected edge.
     */
    public void onSelectedEdge(Edge edge) {
        removeNode.setEnabled(false);
        connectNodes.setEnabled(false);
        if(edge.getArmies().isEmpty()) {
            disconnectNodes.setEnabled(true);
        }
    }

    /**
     * when an edge is unselected.
     */
    public void onUnselectedEdge() {
        removeNode.setEnabled(false);
        connectNodes.setEnabled(false);
        disconnectNodes.setEnabled(false);
    }

    /**
     * when a node is selected.
     * @param node the selected node.
     */
    public void onSelect(Node node) {
        if(node.getArmies().isEmpty()) {
            removeNode.setEnabled(true);
        }
        connectNodes.setEnabled(true);
        disconnectNodes.setEnabled(false);
    }

    /**
     * when a node is unselected.
     */
    public void onUnSelect() {
        removeNode.setEnabled(false);
        connectNodes.setEnabled(false);
        disconnectNodes.setEnabled(false);
    }
}
