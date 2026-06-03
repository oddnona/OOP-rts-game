package nl.rug.oop.rts.controller;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * class that handles the mouse inputs.
 */
public class MouseManager extends MouseAdapter implements MouseSubject {

    /**
     * list of all the observers that respond to the mouse events.
     */
    private List<MouseObserver> observers;
    /**
     * the  position the mouse was clicked last time.
     */
    private Point lastDragPoint;
    /**
     * the mouse button that was pressed last time.
     */
    private int button;

    /**
     * constructor for the mouse manager.
     */
    public MouseManager() {
        observers = new ArrayList<>();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        for(MouseObserver observer : new ArrayList<>(observers)) {
            observer.click(e.getPoint());
        }
        notifyObservers();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        lastDragPoint = e.getPoint();
        button = e.getButton();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        for(MouseObserver observer : observers) {
            observer.hover(e.getPoint());
        }
        notifyObservers();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        int yOffset = e.getY() - lastDragPoint.y;
        int xOffset = e.getX() - lastDragPoint.x;
        for(MouseObserver observer : observers) {
            if(button == MouseEvent.BUTTON1) {
                observer.drag(e.getPoint());
            } else if(button == MouseEvent.BUTTON3) {
                observer.drag(xOffset, yOffset);
            }
        }
        lastDragPoint = e.getPoint();
        notifyObservers();
    }

    @Override
    public void addObserver(MouseObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(MouseObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(MouseObserver observer : observers) {
            observer.refresh();
        }
    }

    /**
     * getter for the mouse observers.
     * @return the list of observers.
     */
    public List<MouseObserver> getObservers() {
        return observers;
    }
}
