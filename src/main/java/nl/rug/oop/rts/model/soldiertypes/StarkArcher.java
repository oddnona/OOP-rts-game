package nl.rug.oop.rts.model.soldiertypes;

import nl.rug.oop.rts.model.Archer;

/**
 * An archr epresenting House Stark.
 * This archer has custom stats reflecting Stark discipline and resilience.
 */
public class StarkArcher extends Archer {
    /**
     * Creates a new Stark archer with preset health and damage values.
     */
    public StarkArcher(){
        health=120;
        damage=10;
        description = " Stark Archers. 'Winter is coming' - some explanation based on the stats.";
    }
}