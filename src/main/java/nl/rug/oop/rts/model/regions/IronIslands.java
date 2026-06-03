package nl.rug.oop.rts.model.regions;

import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.soldiertypes.FreeFolkWarrior;
import nl.rug.oop.rts.model.soldiertypes.GreyjoySoldier;

/**
 * the Iron Islands region.
 */
public class IronIslands extends Region{

    /**
     * constructor for the region.
     */
    public IronIslands() {
        graph = new Graph();
        name = "The Iron Islands";
        graph.erasePreviousGraph();
        Army army = new Army(new GreyjoySoldier(), 190);
        army.setHouse(house);
        armies.add(army);
    }
}
