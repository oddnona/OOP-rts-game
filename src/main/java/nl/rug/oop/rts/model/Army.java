package nl.rug.oop.rts.model;

import nl.rug.oop.rts.json.JSONObject;
import nl.rug.oop.rts.model.nodes.Node;

/**
 * class that represents an army of soldiers.
 */
public class Army implements JSONSerializable{
    /**
     * the amount of soldiers the army has.
     */
    private int amount;
    /**
     * the position of the army on the edge.
     */
    private int positionOnTheEdge;
    /**
     * the type of soldiers the army has.
     */
    private Soldier soldier;
    /**
     * for what house the army is fighting for.
     */
    private House house;
    /**
     * the node the army moves to on the next phase.
     */
    private Node nextNode;
    /**
     * the edge the army moves to on the next phase.
     */
    private Edge nextEdge;
    /**
     * the previous node the army was before it moved.
     * it is mostly used for drawing.
     */
    private Node previousNode;
    /**
     * the previous edge the army was before it moved.
     */
    private Edge previousEdge;
    /**
     * boolean that states if the army is moving.
     */
    private boolean moving;
    /**
     * boolean that states if the army is in a battle.
     */
    private boolean inBattle;
    /**
     * the current frame of an animation.
     */
    private int frames;
    /**
     * the maximum frames in an animation.
     */
    private int maxFrames;

    /**
     * constructor for the army.
     * @param soldier the soldier type.
     * @param amount the amount of soldiers.
     */
    public Army(Soldier soldier, int amount) {
        this.amount =  amount;
        this.soldier = soldier;
        frames = 0;
        maxFrames = 16;
    }

    /**
     * getter for the amount of soldiers.
     * @return the current amount of soldiers.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * setter for the amount of soldiers.
     * @param amount the new amount of soldiers.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * getter for the soldier type.
     * @return the soldier type.
     */
    public Soldier getSoldier() {
        return soldier;
    }

    /**
     * getter for the house of the army.
     * @return the current house of the army.
     */
    public House getHouse() {
        return house;
    }

    /**
     * setter for the house of the army.
     * @param house the new house the army is fighting for.
     */
    public void setHouse(House house) {
        this.house = house;
    }

    /**
     * setter for the position on the edge.
     * @param positionOnTheEdge the new position on the edge.
     */
    public void setPositionOnTheEdge(int positionOnTheEdge) {
        this.positionOnTheEdge = positionOnTheEdge;
    }

    /**
     * getter for the position.
     * @return the current position on the edge.
     */
    public int getPositionOnTheEdge() {
        return positionOnTheEdge;
    }

    @Override
    public String toString() {
        if(amount > 1) {
            return amount + " " + soldier.toString() + "s";
        }
        return "A " + soldier.toString();
    }

    /**
     * seter for the next node.
     * @param nextNode the next node the army goes to.
     */
    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    /**
     * setter for the next edge.
     * @param nextEdge the next edge the army goes to.
     */
    public void setNextEdge(Edge nextEdge) {
        this.nextEdge = nextEdge;
    }

    /**
     * getter for the next node.
     * @return the next node the army goes to.
     */
    public Node getNextNode() {
        return nextNode;
    }

    /**
     * getter for the next edge.
     * @return the next edge the army goes to.
     */
    public Edge getNextEdge() {
        return nextEdge;
    }

    /**
     * getter for the previous node.
     * @return the previous node.
     */
    public Node getPreviousNode() {
        return previousNode;
    }

    /**
     * setter for the previous node.
     * @param previousNode the new previous node.
     */
    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }

    /**
     * getter for the previous edge.
     * @return the previous edge.
     */
    public Edge getPreviousEdge() {
        return previousEdge;
    }

    /**
     * setter for the previous edge.
     * @param previousEdge the previous edge.
     */
    public void setPreviousEdge(Edge previousEdge) {
        this.previousEdge = previousEdge;
    }

    /**
     * setter for the moving state.
     * @param moving the new moving state.
     */
    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    /**
     * getter for the moving state.
     * @return the current moving state.
     */
    public boolean isMoving() {
        return moving;
    }

    /**
     * setter for the in battle state.
     * @param inBattle the new in battle state.
     */
    public void setinBattle(boolean inBattle) {
        this.inBattle = inBattle;
    }

    /**
     * getter for the in battle state.
     * @return the current in battle state.
     */
    public boolean isinBattle() {
        return inBattle;
    }

    /**
     * getter for the current frame in the animation.
     * @return the current frame in the animation.
     */
    public int getFrames() {
        return frames;
    }

    /**
     * getter for the max frames in the animation.
     * @return the max frames in the animation.
     */
    public int getMaxFrames() {
        return maxFrames;
    }

    /**
     * moves the frames in the animation.
     */
    public void animate() {
        frames++;
        if(frames >= maxFrames) {
            frames = 0;
        }
    }

    @Override
    public Object serialize() {
        JSONObject object = new JSONObject();

        if (soldier != null && soldier.armyType != null) {
            object.put("Name", soldier.armyType.toString());
        } else {
            object.put("Name", null);
        }
        object.put("Faction", house != null ? house.name() : null);
        object.put("Unit", soldier != null ? soldier.serialize() : null);
        object.put("UnitCount", amount);

        return object;
    }
}