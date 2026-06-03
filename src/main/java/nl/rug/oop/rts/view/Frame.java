package nl.rug.oop.rts.view;

import nl.rug.oop.rts.controller.*;
import nl.rug.oop.rts.model.*;
import nl.rug.oop.rts.model.nodes.Node;
import nl.rug.oop.rts.view.Buttons.soldierButtons.*;
import nl.rug.oop.rts.view.regionViews.Panel;
import nl.rug.oop.rts.view.regionViews.RegionView;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * this class creates the main window, which contains the main panel and a menu bar.
 */
public class Frame extends JFrame implements StateObserver, SelectNodeObserver, SelectEdgeObserver{
    /**
     * the panel where the graph is drawn.
     */
    private Panel panel;
    /**
     * the menu bar that can modify the graph.
     */
    private MenuBar menuBar;
    /**
     * a split pane to show the menus.
     */
    private JSplitPane splitPane;
    /**
     * the main map panel.
     */
    private MainMap mainMap;
    /**
     * the settings panel.
     */
    private SettingsPanel settingsPanel;
    /**
     * the panel to edit the selected nodes.
     */
    private EditNodePanel editNodePanel;
    /**
     * controller for the edit edge panel.
     */
    private OptionEdgeController optionEdgeController;
    /**
     * the panel to edit the selected nodes.
     */
    private EditEdgePanel editEdgePanel;
    /**
     * controller for the edit edge panel.
     */
    private OptionNodeController optionNodeController;
    /**
     * the house panel.
     */
    private HousePanel housePanel;
    /**
     * the game model state.
     */
    private Game game;
    /**
     * a map that connects the army type with the respective soldier button.
     */
    private Map<ArmyType, SoldierButton> soldierMap;

    //edits for the default UI manager, mostly buttons and popups.
    static {
        Color pink = new ColorUIResource(255, 243, 230);
        Color pinker = new ColorUIResource(245, 235, 215);
        Color black = new ColorUIResource(0, 0, 0);
        UIManager.put("Button.focus", pink);
        UIManager.put("nimbusFocus", pink);
        UIManager.put("Component.focusColor", pink);
        UIManager.put("Button.focusedBorderColor", pink);
        UIManager.put("Button.hoverBorderColor", pink);
        UIManager.put("Button.default.hoverBorderColor", pink);
        UIManager.put("OptionPane.background", black);
        UIManager.put("Panel.background", black);
        UIManager.put("Button.default.background", pinker);
        UIManager.put("Button.default.pressedBackground", pink);
        UIManager.put("Button.default.foreground", black);
        UIManager.put("ComboBox.selectionBackground", pink);
        UIManager.put("ComboBox.selectionForeground", black);
        UIManager.put("List.selectionBackground", black);
        UIManager.put("List.selectionInactiveBackground", black);
        UIManager.put("OptionPane.icon.informationColor", pinker);
        UIManager.put("OptionPane.icon.questionColor", pinker);
        UIManager.put("OptionPane.icon.warningColor", pinker);
        UIManager.put("OptionPane.icon.errorColor", pinker);
        UIManager.put("TextField.background", black);
        UIManager.put("TextField.selectionBackground", black);
    }

    /**
     * constructor for the frame, sets up the frame.
     * @param game the game model.
     */
    public Frame(Game game) {
        this.game = game;
        soldierMap = new HashMap<>();
        setupSoldierButtons();
        setTitle("Assignment 3");
        setSize(new Dimension(1000, 700));
        setLocationRelativeTo(null);
        menuBar = new MenuBar();
        settingsPanel = new SettingsPanel();
        game.addObserver(this);
        getContentPane().add(settingsPanel);
        setJMenuBar(menuBar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    /**
     * sets up the soldier button map.
     */
    private void setupSoldierButtons() {
        soldierMap.put(ArmyType.ARRYN_ARCHER, new ArrynButton());
        soldierMap.put(ArmyType.ARRYN_SOLDIER, new ArrynButton());
        soldierMap.put(ArmyType.BARATHEON_ARCHER, new BaratheonButton());
        soldierMap.put(ArmyType.BARATHEON_FIGHTER, new BaratheonButton());
        soldierMap.put(ArmyType.DOTHRAKI_ARCHER, new DothrakiButton());
        soldierMap.put(ArmyType.DOTHRAKI_RIDER, new DothrakiButton());
        soldierMap.put(ArmyType.DRAGON, new DragonButton());
        soldierMap.put(ArmyType.FACELESS_MAN, new FacelessManButton());
        soldierMap.put(ArmyType.GREYJOY_SOLDIER, new GreyjoyButton());
        soldierMap.put(ArmyType.GREYJOY_ARCHER, new GreyjoyButton());
        soldierMap.put(ArmyType.FREE_FOLK_ARCHER, new FreeFolkButton());
        soldierMap.put(ArmyType.FREE_FOLK_WARRIOR, new FreeFolkButton());
        soldierMap.put(ArmyType.KINGS_GUARD, new KingsGuardButton());
        soldierMap.put(ArmyType.LANNISTER_ARCHER, new LannisterButton());
        soldierMap.put(ArmyType.LANNISTER_SOLDIER, new LannisterButton());
        soldierMap.put(ArmyType.MARTELL_FIGHTER, new MartellButton());
        soldierMap.put(ArmyType.MARTELL_SPEARMAN, new MartellButton());
        soldierMap.put(ArmyType.STARK_ARCHER, new StarkButton());
        soldierMap.put(ArmyType.STARK_SOLDIER, new StarkButton());
        soldierMap.put(ArmyType.TYRELL_SOLDIER, new TyrellButton());
        soldierMap.put(ArmyType.TYRELL_ARCHER, new TyrellButton());
        soldierMap.put(ArmyType.UNSULLIED, new UnsulliedButton());
    }

    /**
     * getter for the panel.
     * @return the current state of the panel.
     */
    public Panel getPanel() {
        return panel;
    }

    /**
     * getter for the menu bar.
     * @return the menu bar.
     */
    public MenuBar getMenu() {
        return menuBar;
    }

    /**
     * getter for the main map panel.
     * @return the main map panel.
     */
    public MainMap getMainMap() {
        return mainMap;
    }

    /**
     * getter for the settings panel.
     * @return the settings panel.
     */
    public SettingsPanel getSettingsPanel() {
        return settingsPanel;
    }

    /**
     * getter for the house panel.
     * @return the house panel.
     */
    public HousePanel getHousePanel() {
        return housePanel;
    }

    @Override
    public void enterMainMap(Game game) {
        menuBar.getMap().setEnabled(false);
        menuBar.getAddNode().setEnabled(false);
        menuBar.getSimulateStep().setEnabled(false);
        menuBar.getUndo().setEnabled(false);
        getContentPane().removeAll();
        getContentPane().add(mainMap);
        revalidate();
        repaint();
    }

    @Override
    public void enterRegion(Game game) {
        menuBar.getAddNode().setEnabled(true);
        menuBar.getMap().setEnabled(true);
        menuBar.getSimulateStep().setEnabled(true);
        menuBar.getUndo().setEnabled(false);
        getContentPane().removeAll();
        RegionView regionView = null;
        for(RegionView regionView1 : mainMap.getRegionViews()) {
            if(regionView1.getRegion() == game.getPlayer().getCurrentRegion()) {
                regionView = regionView1;
                break;
            }
        }
        panel = regionView.getPanel();
        getContentPane().add(panel);
        revalidate();
        repaint();
    }

    @Override
    public void startGame(Game game) {
        getContentPane().removeAll();
        mainMap = new MainMap(game.getMap());
        getContentPane().add(mainMap);
        revalidate();
        repaint();
    }

    @Override
    public void setNewHouse(Game game) {
        housePanel = new HousePanel();
        getContentPane().removeAll();
        getContentPane().add(housePanel);
        revalidate();
        repaint();
    }

    @Override
    public void onSelect(Node node) {
        editNodePanel = new EditNodePanel(node, soldierMap, game.getPlayer());
        optionNodeController = new OptionNodeController(editNodePanel, game.getPlayer());
        game.addArmyObserver(editNodePanel);
        game.addArmyObserver(optionNodeController);
        getContentPane().removeAll();
        menuBar.onSelect(node);
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, editNodePanel, panel);
        splitPane.setDividerLocation(450);
        getContentPane().add(splitPane);
        revalidate();
        repaint();
    }

    @Override
    public void onUnSelect(Node node) {
        if(editNodePanel != null && editNodePanel.getNode() == node && !editNodePanel.getNode().isSelected()) {
            menuBar.onUnSelect();
            getContentPane().removeAll();
            getContentPane().add(panel);
            revalidate();
            repaint();
        }
    }

    @Override
    public void onSelectedEdge(Edge edge) {
        editEdgePanel = new EditEdgePanel(edge, soldierMap, game.getPlayer());
        optionEdgeController = new OptionEdgeController(editEdgePanel, game.getPlayer());
        game.addArmyObserver(editEdgePanel);
        game.addArmyObserver(optionEdgeController);
        menuBar.onSelectedEdge(edge);
        getContentPane().removeAll();
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, editEdgePanel, panel);
        splitPane.setDividerLocation(450);
        getContentPane().add(splitPane);
        revalidate();
        repaint();
    }

    @Override
    public void onUnselectedEdge(Edge edge) {
        if(editEdgePanel != null && editEdgePanel.getEdge() == edge && !editEdgePanel.getEdge().isSelected()) {
            if(editNodePanel != null && !editNodePanel.getNode().isSelected()) {
                menuBar.onUnselectedEdge();
                getContentPane().removeAll();
                getContentPane().add(panel);
                revalidate();
                repaint();
            }
        }
    }
}