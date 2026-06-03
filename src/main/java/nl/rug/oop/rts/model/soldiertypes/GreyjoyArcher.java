package nl.rug.oop.rts.model.soldiertypes;

import nl.rug.oop.rts.model.Archer;

/**
 * An archer representing House Greyjoy.
 * This archer has custom stats reflecting the Greyjoys' ferocity and precision.
 */
public class GreyjoyArcher extends Archer {
    /**
     * Creates a new Greyjoy archer with preset health and damage values.
     */
    public GreyjoyArcher() {
        health=100;
        damage=100;
        description = " Greyjoy Archers. 'What is dead may never die' - some explanation based on the stats.";
    }
}
