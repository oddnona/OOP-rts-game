package nl.rug.oop.rts.controller;

import java.awt.*;

/**
 * observer for the observer pattern for the mouse inputs.
 */
public interface MouseObserver {

    /**
     * when the mouse is clicked.
     * @param position the position of the mouse when it was clicked.
     */
    void click(Point position);

    /**
     * when the mouse is dragged.
     * @param position the current position of the mouse.
     */
    void drag(Point position);

    /**
     * when the mouse is dragged.
     * @param xOffset the x offset from where it was dragged last time and where it ended.
     * @param yOffset the y offset from where it was dragged last time and where it ended.
     */
    void drag(int xOffset, int yOffset);

    /**
     * when the mouse is moved.
     * @param position the position of the mouse.
     */
    void hover(Point position);

    /**
     * refreshes the panel when a mouse event happened.
     */
    void refresh();
}
