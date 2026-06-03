package nl.rug.oop.rts.view;

/**
 * observer that updated the view when the animation of the army happens.
 */
public interface AnimateArmyObserver {
    /**
     * does something when the animation happens.
     */
    void onAnimate();
}
