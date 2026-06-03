package nl.rug.oop.rts.model.regions;

import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.soldiertypes.DothrakiRider;
import nl.rug.oop.rts.model.soldiertypes.FreeFolkWarrior;

/**
 * the dothraki sea region.
 */
public class DothrakiSea extends Region{

    /**
     * constructor for the region.
     */
    public DothrakiSea() {
        graph = new Graph();
        name = "The Dothraki Sea";
        graph.erasePreviousGraph();
        Army army = new Army(new DothrakiRider(), 115);
        army.setHouse(house);
        armies.add(army);
    }

}
