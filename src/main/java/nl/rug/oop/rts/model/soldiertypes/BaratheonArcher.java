package nl.rug.oop.rts.model.soldiertypes;

import nl.rug.oop.rts.model.Archer;

/**
 * A soldier representing House Baratheon.
 * This archer has strong stats, reflecting the raw power and endurance of Baratheon warriors.
 */
public class BaratheonArcher extends Archer {
    /**
     * Creates a new Baratheon archer with preset health and damage values.
     */
    public BaratheonArcher(){
        health=100;
        damage=100;
        description = " Baratheon Archers. 'Ours is the fury' - some explanation based on the stats.";
    }
}
