package nl.rug.oop.rts.model.regions;

import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.soldiertypes.FreeFolkWarrior;
import nl.rug.oop.rts.model.soldiertypes.LannisterSoldier;

/**
 * westerlands region.
 */
public class Westerlands extends Region{

    /**
     * constructor for the region.
     */
    public Westerlands() {
        graph = new Graph();
        name = "Westerlands";
        graph.erasePreviousGraph();
        Army army = new Army(new LannisterSoldier(), 1600);
        army.setHouse(house);
        armies.add(army);
    }
}
