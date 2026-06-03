package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Game;
import nl.rug.oop.rts.view.Sounds;
import nl.rug.oop.rts.view.regionViews.RegionView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * controller for the region view inside the map panel.
 */
public class RegionController implements MouseObserver, StateSubject{
    /**
     * the region view to be modified.
     */
    private RegionView regionView;
    /**
     * the game.
     */
    private Game game;
    /**
     * the list of observers to update the game state.
     */
    private List<StateObserver> observers;
    /**
     * sounds.
     */
    private Sounds sounds;

    /**
     * constructor for the controller.
     * @param game the game.
     * @param regionView the region view to be updated.
     */
    public RegionController(Game game, RegionView regionView) {
        this.regionView = regionView;
        this.game = game;
        sounds = new Sounds();
        observers = new ArrayList<>();
    }

    @Override
    public void click(Point position) {
        int x = (position.x - regionView.getPosition().x);
        int y = (position.y - regionView.getPosition().y);
        if(Math.sqrt(x * x + y * y) <= (double)(100 / 2)) {
            sounds.playSound("transitionsound.wav");
            game.setCurrentRegion(regionView.getRegion());
            sounds.playSound(regionView.getPanel().getSongName());
        }
    }

    @Override
    public void drag(Point position) {

    }

    @Override
    public void drag(int xOffset, int yOffset) {

    }

    @Override
    public void hover(Point position) {
        int x = (position.x - regionView.getPosition().x);
        int y = (position.y - regionView.getPosition().y);
        if(Math.sqrt(x * x + y * y) <= (double)(100 / 2)) {
            regionView.setSelectable(true);
        } else {
            regionView.setSelectable(false);
        }
    }

    /**
     * moves the region view along with the map in the x direction.
     * @param xOffset the x amount moved by the mouse.
     */
    public void moveX(int xOffset) {
        int x = regionView.getPosition().x + xOffset;
        regionView.setPosition(new Point(x, regionView.getPosition().y));
    }

    /**
     * moves the region view along with the map in the y direction.
     * @param yOffset the y amount moved by the mouse.
     */
    public void moveY(int yOffset) {
        int y = regionView.getPosition().y + yOffset;
        regionView.setPosition(new Point(regionView.getPosition().x, y));
    }

    @Override
    public void refresh() {

    }

    @Override
    public void addObserver(StateObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(StateObserver observer) {
        observers.remove(observer);
    }

    public Sounds getSounds() {
        return sounds;
    }
}
