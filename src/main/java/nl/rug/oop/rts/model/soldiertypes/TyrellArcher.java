package nl.rug.oop.rts.model.soldiertypes;

import nl.rug.oop.rts.model.Archer;

/**
 * An archer representing House Tyrell.
 * This acrher has custom stats reflecting the strength and pride of House Tyrell.
 */
public class TyrellArcher extends Archer {
    /**
     * Creates a new Tyrell acrher with preset health and damage values.
     */
    public TyrellArcher() {
        health=100;
        damage=50;
        description = " Tyrell Archers. 'Growing strong' - some explanation based on the stats.";
    }
}
