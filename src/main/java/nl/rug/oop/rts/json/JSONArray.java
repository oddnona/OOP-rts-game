package nl.rug.oop.rts.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A JSON Array which can be read from and written to.
 * JSON Arrays contains items in order.
 */
public class JSONArray {
    /**
     * Holds the items in this JSON Array.
     */
    private List<Object> list;

    /**
     * Creates an empty JSON Array.
     */
    public JSONArray() {
        this.list = new ArrayList<>();
    }

    /**
     * Gets the object at the specified index.
     * @param index ranging from 0 to size-1.
     * @return the item at the index or null.
     * @throws IndexOutOfBoundsException if the specified index is less than 0 or greater than the size of the JSON
     * Array.
     */
    public Object get(int index) {
        return list.get(index);
    }

    /**
     * Gets the object at the specified index as a String.
     * @param index ranging from 0 to size-1.
     * @return the item at the index or null.
     * @throws IndexOutOfBoundsException if the specified index is less than 0 or greater than the size of the JSON
     * Array.
     */
    public String getString(int index) {
        return (String) list.get(index);
    }

    /**
     * Gets the object at the specified index as an Integer.
     * @param index ranging from 0 to size-1.
     * @return the item at the index or null.
     * @throws IndexOutOfBoundsException if the specified index is less than 0 or greater than the size of the JSON
     * Array.
     */
    public Integer getInt(int index) {
        return (Integer) list.get(index);
    }

    /**
     * Gets the object at the specified index as a Long.
     * @param index ranging from 0 to size-1.
     * @return the item at the index or null.
     * @throws IndexOutOfBoundsException if the specified index is less than 0 or greater than the size of the JSON
     * Array.
     */
    public Long getLong(int index) {
        return (Long) list.get(index);
    }

    /**
     * Gets the object at the specified index as a Float.
     * @param index ranging from 0 to size-1.
     * @return the item at the index or null.
     * @throws IndexOutOfBoundsException if the specified index is less than 0 or greater than the size of the JSON
     * Array.
     */
    public Float getFloat(int index) {
        return (Float) list.get(index);
    }

    /**
     * Gets the object at the specified index as a Double.
     * @param index ranging from 0 to size-1.
     * @return the item at the index or null.
     * @throws IndexOutOfBoundsException if the specified index is less than 0 or greater than the size of the JSON
     * Array.
     */
    public Double getDouble(int index) {
        return (Double) list.get(index);
    }

    /**
     * Gets the object at the specified index as a JSONObject.
     * @param index ranging from 0 to size-1.
     * @return the item at the index or null.
     * @throws IndexOutOfBoundsException if the specified index is less than 0 or greater than the size of the JSON
     * Array.
     */
    public JSONObject getObject(int index) {
        return (JSONObject) list.get(index);
    }

    /**
     * Gets the object at the specified index as a JSONArray.
     * @param index ranging from 0 to size-1.
     * @return the item at the index or null.
     * @throws IndexOutOfBoundsException if the specified index is less than 0 or greater than the size of the JSON
     * Array.
     */
    public JSONArray getArray(int index) {
        return (JSONArray) list.get(index);
    }

    /**
     * Adds an item to this JSON Array.
     * @param value the value of the item (must be JSON serializable).
     */
    public void add(Object value) {
        if (value != null && !Types.getClasses().contains(value.getClass())) {
            throw new UnsupportedOperationException("Cannot add \"" + value +
                    "\" as it is of type \"" + value.getClass() + "\", which cannot be serialized to JSON.");
        }
        list.add(value);
    }

    /**
     * Adds an item to this JSON Array at the specified index. Shifts the item at that position and any subsequent items
     * to the right.
     * @param index the index to insert at (must be greater than 0 and smaller than the size of the array).
     * @param value the value of the item (must be JSON serializable).
     * @throws IndexOutOfBoundsException if the specified index is less than 0 or greater than the size of the JSON
     * Array.
     */
    public void add(int index, Object value) {
        if (value != null && !Types.getClasses().contains(value.getClass())) {
            throw new UnsupportedOperationException("Cannot add \"" + value +
                    "\" as it is of type \"" + value.getClass() + "\", which cannot be serialized to JSON.");
        }
        list.add(index, value);
    }

    /**
     * Adds a collection of items to this JSON Array.
     * @param range the collection of the items (must be JSON serializable).
     */
    public void addAll(Collection<Object> range) {
        for (Object obj : range) {
            if (obj != null && !Types.getClasses().contains(obj.getClass())) {
                throw new UnsupportedOperationException("Cannot add \"" +
                        obj + "\" as it is of type \"" + obj.getClass() + "\", which cannot be serialized to JSON.");
            }
        }
        list.addAll(range);
    }

    /**
     * Adds a collection of items to this JSON Array at the specified index. Shifts the item at that position and any
     * subsequent items to the right by the size of the added collection.
     * @param index the index to begin inserting at (must be greater than 0 and smaller than the size of the array).
     * @param range the collection of the items (must be JSON serializable).
     * @throws IndexOutOfBoundsException if the specified index is less than 0 or greater than the size of the JSON
     * Array.
     */
    public void addAll(int index, Collection<Object> range) {
        for (Object obj : range) {
            if (obj != null && !Types.getClasses().contains(obj.getClass())){
                throw new UnsupportedOperationException("Cannot add \"" +
                        obj + "\" as it is of type \"" + obj.getClass() + "\", which cannot be serialized to JSON.");
            }
        }
        list.addAll(index, range);
    }

    /**
     * Sets an item at a specified position, replacing the existing item.
     * @param index the index to replace.
     * @param value the new value to replace the existing value.
     * @throws IndexOutOfBoundsException if the specified index is less than 0 or greater than the size of the JSON
     * Array.
     */
    public void set(int index, Object value) {
        if (value != null && !Types.getClasses().contains(value.getClass())) {
            throw new UnsupportedOperationException("Cannot set \"" + value +
                    "\" as it is of type \"" + value.getClass() + "\", which cannot be serialized to JSON.");
        }
        list.set(index, value);
    }

    /**
     * Removes an item at a specified position, returning it.
     * @param index the index at which to remove
     * @return the removed item
     * @throws IndexOutOfBoundsException if the specified index is less than 0 or greater than the size of the JSON
     * Array.
     */
    public Object remove(int index) {
        return list.remove(index);
    }

    /**
     * Removes an item by its instance.
     * @param obj the instance of the item to remove.
     * @return whether the item was removed (the list contained it).
     */
    public boolean remove(Object obj) {
        return list.remove(obj);
    }

    /**
     * Gets an iterable collection of all the items in this JSON Array.
     * @return the collection of items.
     */
    public Collection<Object> getItems() {
        return new ArrayList<>(list);
    }

    /**
     * Gets the number of items in this JSON Array.
     * @return the size as an int.
     */
    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        return new JSONStringifier(this).stringify();
    }
}
