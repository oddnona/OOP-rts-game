package nl.rug.oop.rts.model;

import nl.rug.oop.rts.json.JSONObject;

/**
 * class that represents a soldier.
 */
public abstract class Soldier implements JSONSerializable {
    /**
     * the soldier's damage.
     */
    protected int damage;
    /**
     * the name of the soldier.
     */
    protected String name;
    /**
     * the army type.
     */
    protected ArmyType armyType;
    /**
     * the soldier's health.
     */
    protected int health;
    /**
     * description of the army type.
     */
    protected String description;
    /**
     * checks if the soldier is already fighting or not.
     */
    protected boolean hasArmor;

    /**
     * getter for the damage.
     * @return the damage.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * getter for the description.
     * @return the description string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * getter for health.
     * @return the current health.
     */
    public int getHealth() {
        return health;
    }

    /**
     * setter for the health.
     * @param health the new health.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * adds armor to the soldiers if they don't already have.
     */
    public void addArmor() {
        hasArmor = true;
        health = health + 15;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * getter for the type of army.
     * @return the type of army.
     */
    public ArmyType getArmyType() {
        return armyType;
    }

    /**
     * getter for has armor state.
     * @return the current has armor state.
     */
    public boolean hasArmor() {
        return hasArmor;
    }

    @Override
    public Object serialize() {
        JSONObject object = new JSONObject();
        object.put("Name", name);
        object.put("Strength", damage);
        object.put("Health", health);
        object.put("Description", description);
        return object;
    }
}
