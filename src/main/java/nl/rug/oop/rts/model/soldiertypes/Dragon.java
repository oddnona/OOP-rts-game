package nl.rug.oop.rts.model.soldiertypes;

import nl.rug.oop.rts.model.Archer;

/**
 * A dragon used in battle.
 * This powerful unit has extremely high health and damage, representing its devastating force on the battlefield.
 */
public class Dragon extends Archer {

    /**
     * Creates a new Dragon with preset health and damage values.
     */
    public Dragon() {
        health = 9000;
        damage = 800;
        String a = new String(" When the dragons come, your flesh will burn and blister and turn to ash.");
        String b = new String(" Your wives will dance in gowns of fire, shrieking as they burn.");
        String c = new String(" This is their world. For dragons are fire made flesh, and fire is power.");
        description = a+b+c;
    }
}
