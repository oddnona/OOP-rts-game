package nl.rug.oop.rts.model.regions;

import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.soldiertypes.FreeFolkWarrior;
import nl.rug.oop.rts.model.soldiertypes.MartellFighter;

/**
 * dorne region.
 */
public class Dorne extends Region{
    /**
     * constructor for the region.
     */

    public Dorne() {
        graph = new Graph();
        name = "Dorne";
        graph.erasePreviousGraph();
        Army army = new Army(new MartellFighter(), 600);
        army.setHouse(house);
        armies.add(army);
    }
}
