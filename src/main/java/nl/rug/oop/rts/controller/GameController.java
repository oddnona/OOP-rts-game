package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Game;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.nodes.Node;
import nl.rug.oop.rts.view.EdgeView;
import nl.rug.oop.rts.view.Frame;
import nl.rug.oop.rts.view.GraphModelObserver;
import nl.rug.oop.rts.view.Sounds;

import java.util.List;

/**
 * connects the view with the model in the MVC pattern.
 */
public class GameController implements StateObserver, GraphModelObserver {
    /**
     * the mouse manager that handles mouse input.
     */
    private MouseManager mouse;
    /**
     * map controller.
     */
    private MainMapController mainMapController;
    /**
     * the menu controller.
     */
    private MenuController menuController;
    /**
     * the panel controller.
     */
    private PanelController panelController;
    /**
     * the frame controller.
     */
    private Frame frame;
    /**
     * the game view.
     */
    private Game game;

    /**
     * constructor for the controller.
     * @param frame the view.
     * @param game the model.
     */
    public GameController(Game game, Frame frame) {
        this.game = game;
        this.frame = frame;
        mouse = new MouseManager();
        game.addObserver(this);
        SettingsController settingsController = new SettingsController(frame.getSettingsPanel(), game);
        menuController = new MenuController(frame.getMenu(), game);
    }

    @Override
    public void enterMainMap(Game game) {
        for(RegionController regionController : mainMapController.getRegionControllers()) {
            regionController.getSounds().stopSound(frame.getPanel().getSongName());
        }
        game.getPlayer().getCurrentRegion().getGraph().removeObserver(panelController);
        game.getPlayer().getCurrentRegion().getGraph().removeObserver(this);
        for(Node node : game.getPlayer().getCurrentRegion().getGraph().getNodes()) {
            node.removeSelectObserver(frame);
        }
        mouse.getObservers().clear();
        mouse.addObserver(mainMapController);
        mouse.addObserver(mainMapController.getScrollController());
        for(RegionController regionController : mainMapController.getRegionControllers()) {
            mouse.addObserver(regionController);
        }
        for(Node node : game.getPlayer().getCurrentRegion().getGraph().getNodes()) {
            node.getTimer().stop();
        }
        for(Edge edge : game.getPlayer().getCurrentRegion().getGraph().getEdges()) {
            edge.getTimer().stop();
        }
    }

    @Override
    public void enterRegion(Game game) {
        if(!List.of(frame.getPanel().getMouseListeners()).contains(mouse)) {
            frame.getPanel().addMouseListener(mouse);
            frame.getPanel().addMouseMotionListener(mouse);
            frame.getPanel().addMouseWheelListener(mouse);
        }
        panelController = new PanelController(frame.getPanel(), game);
        game.getPlayer().getCurrentRegion().getGraph().addObserver(panelController);
        game.getPlayer().getCurrentRegion().getGraph().addObserver(this);
        for(Node node : game.getPlayer().getCurrentRegion().getGraph().getNodes()) {
            node.getTimer().start();
        }
        for(Edge edge : game.getPlayer().getCurrentRegion().getGraph().getEdges()) {
            edge.getTimer().start();
        }
        redrawGraph(game.getPlayer().getCurrentRegion().getGraph());
    }

    @Override
    public void startGame(Game game) {
        mainMapController = new MainMapController(game, frame.getMainMap());
        frame.getMainMap().addMouseListener(mouse);
        frame.getMainMap().addMouseMotionListener(mouse);
        frame.getMainMap().addMouseWheelListener(mouse);
        mouse.addObserver(mainMapController);
        mouse.addObserver(mainMapController.getScrollController());
        for(RegionController regionController : mainMapController.getRegionControllers()) {
            mouse.addObserver(regionController);
        }
    }

    @Override
    public void setNewHouse(Game game) {
        HouseController houseController = new HouseController(frame.getHousePanel(), game);
    }

    @Override
    public void redrawGraph(Graph graph) {
        if(graph.getPreviousGraph() != null) {
            frame.getMenu().getUndo().setEnabled(true);
        }
        if(panelController != null) {
            for(Node node : graph.getNodes()) {
                if (!node.getSelectNodeObservers().contains(frame)) {
                    node.addSelectObserver(frame);
                    node.addSelectObserver(menuController);
                    node.addObserver(frame.getPanel());
                }
            }
            for(Edge edge : graph.getEdges()) {
                if(!edge.getSelectObservers().contains(frame)) {
                    edge.addSelectObserver(frame);
                    edge.addSelectObserver(menuController);
                    edge.addObserver(frame.getPanel());
                }
            }
            for(EdgeView edgeView : frame.getPanel().getEdgeViews()) {
                game.addArmyObserver(edgeView);
            }

            mouse.getObservers().clear();
            mouse.addObserver(panelController);
            for(NodeController nodeController : panelController.getNodeControllers()) {
                mouse.addObserver(nodeController);
            }
            for(EdgeController edgeController : panelController.getEdgeControllers()) {
                mouse.addObserver(edgeController);
            }
        }
    }
}