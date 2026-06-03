package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Scroll;
import nl.rug.oop.rts.view.ScrollView;
import nl.rug.oop.rts.view.Sounds;

import java.awt.*;

/**
 * controller for the scroll view to open and close it.
 */
public class ScrollController implements MouseObserver {
    /**
     * the scroll view.
     */
    private ScrollView scrollView;
    /**
     * the scroll.
     */
    private Scroll scroll;
    /**
     * sounds.
     */
    private Sounds sounds;

    /**
     * the constructor for the scroll controller.
     * @param scrollView the scroll view.
     * @param scroll the scroll.
     */
    public ScrollController(ScrollView scrollView, Scroll scroll) {
        this.scroll = scroll;
        this.scrollView = scrollView;
        scroll.addObserver(scrollView);
        sounds = new Sounds();
    }

    @Override
    public void click(Point position) {
        if(scrollView.getBounds().contains(position)) {
            scrollView.getTimer().start();
            sounds.playSound("scrollsound.wav");
        } else if(scrollView.isOpen()) {
            scrollView.getTimer().start();
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

    }

    @Override
    public void refresh() {

    }
}
