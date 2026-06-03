package nl.rug.oop.rts.model.soldiertypes;

import nl.rug.oop.rts.model.Archer;

/**
 * A spearman representing House Martell.
 * This spearman has custom stats reflecting the agility and resilience of House Martell.
 */
public class MartellSpearman extends Archer {
    /**
     * Creates a new Martell spearman with preset health and damage values.
     */
    public MartellSpearman() {
        health=100;
        damage=100;
        description = "Martell Archers.'Unbowed, unbent, unbroken' - some explanation based on the stats.";
    }
}
