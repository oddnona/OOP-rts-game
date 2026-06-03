package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.json.JSONArray;
import nl.rug.oop.rts.json.JSONObject;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Game;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.nodes.ArmyCamp;
import nl.rug.oop.rts.model.nodes.Node;
import nl.rug.oop.rts.model.regions.Region;
import nl.rug.oop.rts.view.MenuBar;
import nl.rug.oop.rts.view.Sounds;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * handles the behavior of the buttons of the menu.
 */
public class MenuController implements ActionListener, SelectNodeObserver, SelectEdgeObserver {
    /**
     * the button menu bar.
     */
    private MenuBar menuBar;
    /**
     * the game.
     */
    private Game game;
    /**
     * the selected node.
     */
    private Node selectedNode;
    /**
     * the first node.
     */
    private Node firstNode;
    /**
     * the selected edge.
     */
    private Edge selectedEdge;
    /**
     * true if a first node is selected and waits a second node.
     */
    private boolean waitingForNode;
    /**
     * sounds.
     */
    private Sounds sounds;

    /**
     * controller for the menu.
     * @param menuBar the menu button bar.
     * @param game the game.
     */
    public MenuController(MenuBar menuBar, Game game) {
        this.menuBar = menuBar;
        this.game = game;
        sounds = new Sounds();
        selectedNode = null;
        selectedEdge = null;
        menuBar.getAddNode().addActionListener(this);
        menuBar.getMap().addActionListener(this);
        menuBar.getConnectNodes().addActionListener(this);
        menuBar.getDisconnectNodes().addActionListener(this);
        menuBar.getRemoveNode().addActionListener(this);
        menuBar.getSimulateStep().addActionListener(this);
        menuBar.getSave().addActionListener(this);
        menuBar.getUndo().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == menuBar.getAddNode()) {
            sounds.playSound("buttonsound.wav");
            addNewNode();
        }
        if(e.getSource() == menuBar.getMap()) {
            sounds.playSound("transitionsound.wav");
            returnToMap();
        }
        if(e.getSource() == menuBar.getRemoveNode()) {
            sounds.playSound("buttonsound.wav");
            removeSelectedNode();
        }
        if(e.getSource() == menuBar.getConnectNodes()) {
            sounds.playSound("buttonsound.wav");
            connectTwoNodes();
        }
        if(e.getSource() == menuBar.getDisconnectNodes()) {
            sounds.playSound("buttonsound.wav");
            deleteEdge();
        }
        if(e.getSource() == menuBar.getSimulateStep()) {
            simulate();
        }
        if (e.getSource() == menuBar.getSave()) {
            save();
        }
        if (e.getSource() == menuBar.getUndo()) {
            undo();
        }
    }

    /**
     * adds a new node to the graph.
     */
    private void addNewNode() {
        Graph graph = game.getPlayer().getCurrentRegion().getGraph();
        Random random = new Random();
        Node node = new ArmyCamp("New Node", new Point(500 + random.nextInt(1000), 300 + random.nextInt(800)));
        node.setHouse(game.getPlayer().getHouse());
        game.getPlayer().savePlayer();
        graph.addNode(node);
    }

    /**
     * returns to the main map panel.
     */
    private void returnToMap() {
        game.returnToMap();
    }

    /**
     * deletes the selected node from the graph.
     */
    private void removeSelectedNode() {
        Graph graph = game.getPlayer().getCurrentRegion().getGraph();
        game.getPlayer().savePlayer();
        graph.removeNode(selectedNode);
        selectedNode.setSelected(false);
    }

    /**
     * connects 2 nodes from the graph.
     */
    private void connectTwoNodes() {
        firstNode = selectedNode;
        selectedNode.setSelected(false);
        waitingForNode = true;
    }

    /**
     * deletes an edge from the map.
     */
    private void deleteEdge() {
        Graph graph = game.getPlayer().getCurrentRegion().getGraph();
        game.getPlayer().savePlayer();
        graph.disconnectNodes(selectedEdge);
        selectedEdge.setSelected(false);
    }

    /**
     * simulates one phase.
     */
    private void simulate(){
        game.getPlayer().savePlayer();
        game.simulateStep();
    }

    /**
     * saves the game state into json.
     */
    private void save() {
        if (game == null
                || game.getPlayer() == null
                || game.getPlayer().getCurrentRegion() == null
                || game.getPlayer().getCurrentRegion().getGraph() == null) {
            JOptionPane.showMessageDialog(null, "Cannot save at this time.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JSONArray regionsJSON = new JSONArray();
        List<Region> regions = game.getMap().getRegions();
        for (Region region : regions) {
            if (region.getGraph().getNodes().isEmpty()) {
                continue;
            }
            regionsJSON.add(region.serialize());
        }

        saveFile(regionsJSON);
    }

    /**
     * Opens a file chooser dialog to save a JSON object or array to disk.
     * <p>
     * If the user confirms the save location, the JSON content is written to a
     * `.json` file. If the file name does not end with `.json`, the extension is automatically added.
     * Supports both {@link JSONObject} and {@link JSONArray} as input types.
     * Displays a success or error dialog depending on the result.
     *
     * @param json the JSON object or array to be saved to a file
     */
    private void saveFile(Object json) {
        JFileChooser chooser = new JFileChooser();// Make a file chooser
        chooser.setDialogTitle("Save the game state");// configure it
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON files", "json");
        chooser.setFileFilter(filter);

        int userSelection = chooser.showSaveDialog(null);// show chooser
        if (userSelection == JFileChooser.APPROVE_OPTION) {// process location
            File fileToSave = chooser.getSelectedFile();
            if (!fileToSave.getName().toLowerCase().endsWith(".json")) {
                fileToSave = new File(fileToSave.getParentFile(), fileToSave.getName() + ".json");
            }

            String jsonString = "";
            if (json instanceof JSONObject jsonObject){
                jsonString = jsonObject.toString();
            } else if (json instanceof JSONArray jsonArray){
                jsonString = jsonArray.toString();
            }

            try {
                FileWriter writer = new FileWriter(fileToSave);
                writer.write(jsonString);
                writer.close();
                JOptionPane.showMessageDialog(null, "Saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Saved failed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * undo your last move.
     */
    private void undo() {
        game.undo();
        menuBar.getUndo().setEnabled(false);
    }

    @Override
    public void onSelect(Node node) {
        if(waitingForNode) {
            if(node != firstNode) {
                Graph graph = game.getPlayer().getCurrentRegion().getGraph();
                game.getPlayer().savePlayer();
                graph.connectNodes("new Edge", firstNode, node);
                graph.getEdges().get(graph.getEdges().size() - 1).setHouse(game.getPlayer().getHouse());
            }
            waitingForNode = false;
        }
        selectedNode = node;
    }

    @Override
    public void onUnSelect(Node node) {
        if(selectedNode == node && !selectedNode.isSelected()) {
            selectedNode = null;
        }
    }

    @Override
    public void onSelectedEdge(Edge edge) {
        selectedEdge = edge;
    }

    @Override
    public void onUnselectedEdge(Edge edge) {
        if(selectedEdge == edge && !selectedEdge.isSelected()) {
            selectedEdge = null;
        }
    }
}
