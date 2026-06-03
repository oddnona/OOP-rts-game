package nl.rug.oop.rts.model.soldiertypes;

import nl.rug.oop.rts.model.Archer;

/**
 * An archer representing House Lannister.
 * This archer has custom stats reflecting the power and precision of Lannister forces.
 */
public class LannsiterArcher extends Archer {
    /**
     * Creates a new Lannister archer with preset health and damage values.
     */
    public LannsiterArcher() {
        health=100;
        damage=50;
        description = " Lannister Archers. 'Hear me roar' - some explanation based on the stats.";
    }
}
