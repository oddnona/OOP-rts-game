package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.AddArmyObserver;
import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.nodes.Node;
import nl.rug.oop.rts.model.Player;
import nl.rug.oop.rts.model.events.Event;
import nl.rug.oop.rts.view.Buttons.soldierButtons.SoldierButton;
import nl.rug.oop.rts.view.EditEdgePanel;
import nl.rug.oop.rts.view.Sounds;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * controller for the edge panel.
 */
public class OptionEdgeController implements ActionListener, AddArmyObserver {

    /**
     * the edge panel.
     */
    private EditEdgePanel edgePanel;
    /**
     * sounds.
     */
    private Sounds sounds;
    /**
     * player instance.
     */
    private Player player;
    /**
     * filenames for the sounds.
     */
    private String buttonsound = "buttonsound.wav", popup = "popupsound.wav";

    /**
     * constructor for the controller for the edge panel.
     * @param edgePanel the edge panel to be interacted with.
     * @param player the player to add and remove armies and events.
     */
    public OptionEdgeController(EditEdgePanel edgePanel, Player player) {
        this.edgePanel = edgePanel;
        this.player = player;
        this.edgePanel.getEdge().addArmyObserver(edgePanel);
        this.edgePanel.getEdge().addArmyObserver(this);
        this.edgePanel.getLeftButton().addActionListener(this);
        this.edgePanel.getRightButton().addActionListener(this);
        this.edgePanel.getAddEvent().addActionListener(this);
        this.edgePanel.getRemoveEvent().addActionListener(this);
        this.edgePanel.getShowEvents().addActionListener(this);
        onAddedArmy();
        sounds = new Sounds();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(!edgePanel.getEdge().getArmies().isEmpty()) {
            if (actionEvent.getSource() == edgePanel.getLeftButton()) {
                moveLeft();
                sounds.playSound(buttonsound);
            }
            if (actionEvent.getSource() == edgePanel.getRightButton()) {
                moveRight();
                sounds.playSound(buttonsound);
            }
            if(actionEvent.getSource() == edgePanel.getSoldierButton()) {
                moveArmy();
                sounds.playSound(buttonsound);
            }
        }
        if(actionEvent.getSource() == edgePanel.getAddEvent()) {
            addEvent();
            sounds.playSound(buttonsound);
        }
        if(actionEvent.getSource() == edgePanel.getRemoveEvent()) {
            removeEvent();
            sounds.playSound(buttonsound);
        }
        if(actionEvent.getSource() == edgePanel.getShowEvents()) {
            show();
            sounds.playSound(buttonsound);
        }
    }

    /**
     * shows the list of events on the node in the form of a popup.
     */
    private void show() {
        sounds.playSound(popup);
        if(edgePanel.getEdge().getEvents().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You have no events on this node");
            return;
        }
        JList<Event> events = new JList<>(new Vector<>(edgePanel.getEdge().getEvents()));
        events.setEnabled(false);
        events.setFocusable(false);
        events.setOpaque(false);
        events.setSelectionBackground(null);
        JScrollPane scrollPane = new JScrollPane(events);
        scrollPane.setPreferredSize(new Dimension(300, 150));
        JOptionPane.showMessageDialog(
                null,
                scrollPane,
                "Events on the list",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * when the left arrow is pressed.
     */
    private void moveLeft() {
        edgePanel.setButtonSelected(edgePanel.getButtonSelected() + 1);
        if (edgePanel.getButtonSelected() == edgePanel.getEdge().getArmies().size()) {
            edgePanel.setButtonSelected(0);
        }
        edgePanel.onAddedArmy();
        onAddedArmy();
    }

    /**
     * when the right arrow is pressed.
     */
    private void moveRight() {
        edgePanel.setButtonSelected(edgePanel.getButtonSelected() - 1);
        if (edgePanel.getButtonSelected() == -1) {
            edgePanel.setButtonSelected(edgePanel.getEdge().getArmies().size() - 1);
        }
        edgePanel.onAddedArmy();
        onAddedArmy();
    }

    /**
     * creates a popup and moves an army to the specified node.
     */
    private void moveArmy() {
        List<Node> nodes = new ArrayList<>();
        nodes.add(edgePanel.getEdge().getNode1());
        nodes.add(edgePanel.getEdge().getNode2());
        sounds.playSound(popup);
        Node option = (Node) JOptionPane.showInputDialog(null,
                "Choose an edge to move the army to",
                "Where to?",
                JOptionPane.PLAIN_MESSAGE,
                null,
                nodes.toArray(),
                nodes.get(0));
        if(option != null) {
            Army army = edgePanel.getEdge().getArmies().get(edgePanel.getButtonSelected());
            army.setNextNode(option);
        }
    }

    /**
     * adds an event on the edge.
     */
    private void addEvent() {
        sounds.playSound(popup);
        if(player.getEvents().isEmpty()) {
            JOptionPane.showMessageDialog(null, "There are no events to add");
            return;
        }
        Event event = (Event) JOptionPane.showInputDialog(
                null,
                "Choose an event to add",
                "Add event",
                JOptionPane.PLAIN_MESSAGE,
                null,
                player.getEvents().toArray(),
                player.getEvents().get(0));
        if(event != null) {
            player.savePlayer();
            player.getCurrentRegion().getGraph().savePreviousGraph();
            edgePanel.getEdge().addEvent(event);
            player.getEvents().remove(event);
        }
    }

    /**
     * removes an event from an edge.
     */
    private void removeEvent() {
        sounds.playSound(popup);
        if(edgePanel.getEdge().getEvents().isEmpty()) {
            JOptionPane.showMessageDialog(null, "there are no events on this node");
            return;
        }
        Event event = (Event) JOptionPane.showInputDialog(
                null,
                "Choose an event to remove",
                "Remove event",
                JOptionPane.PLAIN_MESSAGE,
                null,
                edgePanel.getEdge().getEvents().toArray(),
                edgePanel.getEdge().getEvents().get(0)
        );
        if(event != null) {
            player.savePlayer();
            player.getCurrentRegion().getGraph().savePreviousGraph();
            edgePanel.getEdge().removeEvent(event);
            player.getEvents().add(event);
        }
    }

    @Override
    public void onAddedArmy() {
        SoldierButton button = edgePanel.getSoldierButton();
        if (button != null) {
            for (ActionListener listener : button.getActionListeners()) {
                button.removeActionListener(listener);
            }
            button.addActionListener(this);
        }
    }
}
