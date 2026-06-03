package nl.rug.oop.rts.model.regions;

import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.soldiertypes.BaratheonFighter;
import nl.rug.oop.rts.model.soldiertypes.FreeFolkWarrior;

/**
 * stormlands region.
 */
public class Stormlands extends Region{

    /**
     * constructor for the region.
     */
    public Stormlands() {
        graph = new Graph();
        name = "Stormlands";
        graph.erasePreviousGraph();
        Army army = new Army(new BaratheonFighter(), 500);
        army.setHouse(house);
        armies.add(army);
    }
}
