package nl.rug.oop.rts.model.soldiertypes;

import nl.rug.oop.rts.model.ArmyType;
import nl.rug.oop.rts.model.Soldier;

/**
 * A soldier representing a Dothraki rider.
 * This fast and fierce warrior has high damage, reflecting the Dothraki's mastery of mounted combat.
 */
public class DothrakiRider extends Soldier {
    /**
     * Creates a new Dothraki rider with preset health and damage values.
     */
    public DothrakiRider(){
        armyType = ArmyType.DOTHRAKI_RIDER;
        name = "Dothraki Rider";
        health=100;
        damage=70;
        description = " Dothraki Riders. 'Ride or die' - there are no weak ones. Khalasars fight with sheer" +
                " strength. Their blows shatter shields and their horses demolish. Foes die screaming."+
                " Combat is about force and personal glory. Only a fool would face Dothraki in an open field.";
    }
}
