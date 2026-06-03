package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.AddArmyObserver;
import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Player;
import nl.rug.oop.rts.model.events.Event;
import nl.rug.oop.rts.view.Buttons.soldierButtons.SoldierButton;
import nl.rug.oop.rts.view.EditNodePanel;
import nl.rug.oop.rts.view.Sounds;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * controller for the node panel.
 */
public class OptionNodeController implements ActionListener, AddArmyObserver {
    /**
     * the node panel.
     */
    private EditNodePanel nodePanel;
    /**
     * the player model to access the list of armies.
     */
    private Player player;
    /**
     * sounds.
     */
    private Sounds sounds;
    /**
     * filenames for the sounds.
     */
    private String buttonsound = "buttonsound.wav", popup = "popupsound.wav";

    /**
     * constructor for the node panel controller.
     *
     * @param nodePanel the node panel to interact with.
     * @param player the player model.
     */
    public OptionNodeController(EditNodePanel nodePanel, Player player) {
        this.player = player;
        this.nodePanel = nodePanel;
        sounds = new Sounds();
        this.nodePanel.getNode().addArmyObserver(nodePanel);
        this.nodePanel.getNode().addArmyObserver(this);
        this.nodePanel.getLeftButton().addActionListener(this);
        this.nodePanel.getRightButton().addActionListener(this);
        onAddedArmy();
        this.nodePanel.getAddArmy().addActionListener(this);
        this.nodePanel.getRemoveArmy().addActionListener(this);
        this.nodePanel.getAddEvent().addActionListener(this);
        this.nodePanel.getRemoveEvent().addActionListener(this);
        this.nodePanel.getShowEvents().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(!nodePanel.getNode().getArmies().isEmpty()) {
            if (actionEvent.getSource() == nodePanel.getLeftButton()) {
                moveLeft();
                sounds.playSound(buttonsound);
            }
            if (actionEvent.getSource() == nodePanel.getRightButton()) {
                moveRight();
                sounds.playSound(buttonsound);
            }
            if(actionEvent.getSource() == nodePanel.getSoldierButton()) {
                moveArmy();
                sounds.playSound(buttonsound);
            }
        }
        if (actionEvent.getSource() == nodePanel.getAddArmy()) {
            addArmy();
            sounds.playSound(buttonsound);
        }
        if (actionEvent.getSource() == nodePanel.getRemoveArmy()) {
            retreat();
            sounds.playSound(buttonsound);
        }
        if(actionEvent.getSource() == nodePanel.getAddEvent()) {
            addEvent();
            sounds.playSound(buttonsound);
        }
        if(actionEvent.getSource() == nodePanel.getRemoveEvent()) {
            removeEvent();
            sounds.playSound(buttonsound);
        }
        if(actionEvent.getSource() == nodePanel.getShowEvents()) {
            show();
            sounds.playSound(buttonsound);
        }
    }

    /**
     * when the left arrow is pressed.
     */
    private void moveLeft() {
        nodePanel.setButtonSelected(nodePanel.getButtonSelected() + 1);
        if (nodePanel.getButtonSelected() == nodePanel.getNode().getArmies().size()) {
            nodePanel.setButtonSelected(0);
        }
        nodePanel.onAddedArmy();
        onAddedArmy();
    }

    /**
     * when the right arrow is pressed.
     */
    private void moveRight() {
        nodePanel.setButtonSelected(nodePanel.getButtonSelected() - 1);
        if (nodePanel.getButtonSelected() == -1) {
            nodePanel.setButtonSelected(nodePanel.getNode().getArmies().size() - 1);
        }
        nodePanel.onAddedArmy();
        onAddedArmy();
    }

    /**
     * adds an army from the player list to the node.
     */
    private void addArmy() {
        sounds.playSound(popup);
        if (player.getArmies().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You have no armies");
            return;
        }
        Army option = (Army) JOptionPane.showInputDialog(
                null,
                "Choose army to deploy",
                "Add army",
                JOptionPane.PLAIN_MESSAGE,
                null,
                player.getArmies().toArray(),
                player.getArmies().get(0));
        if (option != null) {
            option.setNextEdge(null);
            String text = JOptionPane.showInputDialog(null,
                    "How many troops", "Enter amount", JOptionPane.PLAIN_MESSAGE);
            if (text != null) {
                int amount;
                try {
                    amount = Integer.parseInt(text);
                } catch (NumberFormatException e) {
                    amount = 0;
                }
                if (amount > 0 && amount <= option.getAmount()) {
                    player.savePlayer();
                    option.setAmount(option.getAmount() - amount);
                    Army army = new Army(option.getSoldier(), amount);
                    army.setHouse(player.getHouse());
                    player.getCurrentRegion().getGraph().savePreviousGraph();
                    nodePanel.getNode().deployTroops(army);
                    player.removeEmptyArmies();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid amount", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    /**
     * creates a popup and moves an army to the specified edge.
     */
    private void moveArmy() {
        sounds.playSound(popup);
        if(nodePanel.getNode().getEdges().isEmpty()) {
            JOptionPane.showMessageDialog(null, "The army has nowhere to go");
            return;
        }
        Edge option = (Edge) JOptionPane.showInputDialog(null,
                "Choose an edge to move the army to",
                "Where to?",
                JOptionPane.PLAIN_MESSAGE,
                null,
                nodePanel.getNode().getEdges().toArray(),
                nodePanel.getNode().getEdges().get(0));
        if(option != null) {
            Army army = nodePanel.getNode().getArmies().get(nodePanel.getButtonSelected());
            army.setNextEdge(option);
        }
    }

    /**
     * retreat the armies from an edge.
     */
    private void retreat() {
        sounds.playSound(popup);
        if(nodePanel.getNode().getArmies().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You have no armies");
            return;
        }
        Army option = (Army) JOptionPane.showInputDialog(
                null,
                "Choose army to deploy",
                "Add army",
                JOptionPane.PLAIN_MESSAGE,
                null,
                nodePanel.getNode().getArmies().toArray(),
                nodePanel.getNode().getArmies().get(0));
        if(option != null) {
            player.savePlayer();
            player.getCurrentRegion().getGraph().savePreviousGraph();
            nodePanel.getNode().removeTroops(option);
            player.addArmy(option);
        }
    }

    /**
     * shows the list of events on the node in the form of a popup.
     */
    private void show() {
        sounds.playSound(popup);
        if(nodePanel.getNode().getEvents().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You have no events on this node");
            return;
        }
        JList<Event> events = new JList<>(new Vector<>(nodePanel.getNode().getEvents()));
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
            nodePanel.getNode().addEvent(event);
            player.getEvents().remove(event);
        }
    }

    /**
     * removes an event from a node.
     */
    private void removeEvent() {
        sounds.playSound(popup);
        if(nodePanel.getNode().getEvents().isEmpty()) {
            JOptionPane.showMessageDialog(null, "there are no events on this node");
            return;
        }
        Event event = (Event) JOptionPane.showInputDialog(
                null,
                "Choose an event to remove",
                "Remove event",
                JOptionPane.PLAIN_MESSAGE,
                null,
                nodePanel.getNode().getEvents().toArray(),
                nodePanel.getNode().getEvents().get(0)
        );
        if(event != null) {
            player.savePlayer();
            player.getCurrentRegion().getGraph().savePreviousGraph();
            nodePanel.getNode().removeEvent(event);
            player.getEvents().add(event);
        }
    }

    @Override
    public void onAddedArmy() {
        SoldierButton button = nodePanel.getSoldierButton();
        if (button != null) {
            for (ActionListener listener : button.getActionListeners()) {
                button.removeActionListener(listener);
            }
            button.addActionListener(this);
        }
    }
}
