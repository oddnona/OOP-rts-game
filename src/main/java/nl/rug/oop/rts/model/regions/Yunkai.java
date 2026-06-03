package nl.rug.oop.rts.model.regions;

import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.soldiertypes.FreeFolkWarrior;
import nl.rug.oop.rts.model.soldiertypes.Unsullied;

/**
 * yunkai region.
 */
public class Yunkai extends Region{
    /**
     * constructor for the region.
     */
    public Yunkai() {
        graph = new Graph();
        name = "Yunkai";
        graph.erasePreviousGraph();
        Army army = new Army(new Unsullied(), 3000);
        army.setHouse(house);
        armies.add(army);
    }
}
