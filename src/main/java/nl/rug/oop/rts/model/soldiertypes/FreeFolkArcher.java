package nl.rug.oop.rts.model.soldiertypes;

import nl.rug.oop.rts.model.Archer;

/**
 * An archer representing the Free Folk.
 * This archer has custom stats reflecting the raw strength and survival skills of the Free Folk.
 */
public class FreeFolkArcher extends Archer{
    /**
     * Creates a new Free Folk archer with preset health and damage values.
     */
    public FreeFolkArcher(){
        health=100;
        damage=50;
        description = " Free Folk Archers. Free by nature, strong by choice, beyond the Wall's control. " +
                " - some explanation based on the stats.";
    }
}
