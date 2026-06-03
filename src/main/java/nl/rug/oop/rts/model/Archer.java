package nl.rug.oop.rts.model;

/**
 * class that represents a soldier.
 */

public abstract class Archer {
    /**
     * the soldier's damage.
     */
    protected int damage;
    /**
     * the soldier's health.
     */
    protected int health;
    /**
     * checks if the soldier is already fighting or not.
     */
    protected boolean inFight;
    /**
     * description of the army type.
     */
    protected String description;

    /**
     * getter for the damage.
     * @return the damage.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * getter for health.
     * @return the current health.
     */
    public int getHealth() {
        return health;
    }

    /**
     * getter for the description.
     * @return the description string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter for the health.
     * @param health the new health.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * getter for the in fight attribute.
     * @return the in fight attribute.
     */
    public boolean isInFight() {
        return inFight;
    }

    /**
     * sets the in fight property.
     * @param inFight the new in fight property.
     */
    public void setInFight(boolean inFight) {
        this.inFight = inFight;
    }

    /**
     * attacks a soldier.
     * @param archer the other soldier in fight.
     */
    public void attack(Archer archer) {
        inFight = true;
    }
}
