package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Game;
import nl.rug.oop.rts.model.Map;
import nl.rug.oop.rts.view.MainMap;
import nl.rug.oop.rts.view.regionViews.RegionView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * controller for the main map panel.
 */
public class MainMapController implements MouseObserver{
    /**
     * the panel of the map.
     */
    private MainMap mainMap;
    /**
     * the game.
     */
    private Game game;
    /**
     * the map.
     */
    private Map map;
    /**
     * controller for each region.
     */
    private List<RegionController> regionControllers;
    /**
     * the controller for the scroll view.
     */
    private ScrollController scrollController;

    /**
     * constructor for the controller.
     * @param game the game.
     * @param mainMap the map panel.
     */
    public MainMapController(Game game, MainMap mainMap) {
        this.mainMap = mainMap;
        this.game = game;
        map = game.getMap();
        this.game = game;
        scrollController = new ScrollController(mainMap.getScroll(), game.getMap().getScroll());
        regionControllers = new ArrayList<>();
        for(RegionView regionView : mainMap.getRegionViews()) {
            regionControllers.add(new RegionController(game, regionView));
        }
    }

    /**
     * getter for the scroll controller.
     * @return the scroll controller.
     */
    public ScrollController getScrollController() {
        return scrollController;
    }

    @Override
    public void click(Point position) {
    }

    @Override
    public void drag(Point position) {

    }

    @Override
    public void drag(int xOffset, int yOffset) {
        int x = mainMap.getPosition().x + xOffset;
        int y = mainMap.getPosition().y + yOffset;
        if (x < 0 && x + mainMap.getMapWidth() > mainMap.getWidth()) {
            mainMap.setPosition(new Point(x, mainMap.getPosition().y));
            for(RegionController regionController : regionControllers) {
                regionController.moveX(xOffset);
            }
        }
        if(y < 0 && y + mainMap.getMapHeight() > mainMap.getHeight()) {
            mainMap.setPosition(new Point(mainMap.getPosition().x, y));
            for(RegionController regionController : regionControllers) {
                regionController.moveY(yOffset);
            }
        }
    }

    @Override
    public void hover(Point position) {

    }

    @Override
    public void refresh() {
        mainMap.repaint();
    }

    /**
     * getter for the list of region controllers.
     * @return the current list of controllers.
     */
    public List<RegionController> getRegionControllers() {
        return regionControllers;
    }
}
