package nl.rug.oop.rts.model.soldiertypes;

import nl.rug.oop.rts.model.Archer;

/**
 * An archer representing House Arryn.
 * This acrher has moderate stats, reflecting the noble and defensive nature of the Vale's warriors.
 */
public class ArrynArcher extends Archer {
    /**
     * Creates a new Arryn archer with preset health and damage values.
     */
    public ArrynArcher() {
        health=100;
        damage=50;
        description = " Arryn Archers. 'As high as Honor' - some explanation based on the stats.";
    }
}
