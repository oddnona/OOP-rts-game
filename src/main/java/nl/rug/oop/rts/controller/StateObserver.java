package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Game;

/**
 * observer for when the player changes the state of the game to battle.
 */
public interface StateObserver {

    /**
     * if the state of the game is in the main map.
     * @param game the game.
     */
    void enterMainMap(Game game);

    /**
     * if the state of the game is in a region.
     * @param game the game.
     */
    void enterRegion(Game game);

    /**
     * updated the observer when the user chose a starting house.
     * @param game the game.
     */
    void startGame(Game game);

    /**
     * updated the observer when starting a new game.
     * @param game the game.
     */
    void setNewHouse(Game game);
}
