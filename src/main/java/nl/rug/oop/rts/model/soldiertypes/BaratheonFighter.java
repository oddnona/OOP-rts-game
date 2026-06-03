package nl.rug.oop.rts.model.soldiertypes;

import nl.rug.oop.rts.model.ArmyType;
import nl.rug.oop.rts.model.Soldier;

/**
 * A soldier representing House Baratheon.
 * This fighter has strong stats, reflecting the raw power and endurance of Baratheon warriors.
 */
public class BaratheonFighter extends Soldier {
    /**
     * Creates a new Baratheon fighter with preset health and damage values.
     */
    public BaratheonFighter(){
        armyType = ArmyType.BARATHEON_FIGHTER;
        name = "Baratheon fighter";
        health=120;
        damage=40;
        description = " Baratheon Stags. 'Ours is the fury' - Fighters with overwhelming force and" +
                " aggression."+
                " With warhammers and steel armor, they harden in the battle like the sky during storms."+
                " These are not flashy jousters, these are real men, experienced with gruesome combat.";
    }
}
