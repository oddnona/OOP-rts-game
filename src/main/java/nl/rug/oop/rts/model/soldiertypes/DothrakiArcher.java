package nl.rug.oop.rts.model.soldiertypes;

import nl.rug.oop.rts.model.Archer;

/**
 * An archer representing a Dothraki rider.
 * This fast and fierce archer reflects the Dothraki's mastery of mounted combat.
 */
public class DothrakiArcher extends Archer {
    /**
     * Creates a new Dothraki archer with preset health and damage values.
     */
    public DothrakiArcher() {
        health=100;
        damage=100;
        description = " Dothraki Archers. 'Ride or die' - some explanation based on the stats.";
    }
}
