package nl.rug.oop.rts.model.regions;

import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.Graph;
import nl.rug.oop.rts.model.soldiertypes.FacelessMan;
import nl.rug.oop.rts.model.soldiertypes.FreeFolkWarrior;

/**
 * bravos region.
 */
public class Braavos extends Region{

    /**
     * constructor for the region.
     */
    public Braavos() {
        graph = new Graph();
        name = "Braavos";
        graph.erasePreviousGraph();
        Army army = new Army(new FacelessMan(), 9);
        army.setHouse(house);
        armies.add(army);
    }

}
