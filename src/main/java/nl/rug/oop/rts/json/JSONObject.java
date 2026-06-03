package nl.rug.oop.rts.json;

import java.util.*;

/**
 * A JSON Object which can be read from and written to.
 * JSON Objects contain key/value pairs.
 */
public class JSONObject {

    /**
     * Holds the key/value pairs.
     */
    private Map<String, Object> map;

    /**
     * Creates an empty ordered JSON Object.
     */
    public JSONObject() {
        this.map = new LinkedHashMap<>();
    }

    /**
     * Creates an empty JSON Object.
     * @param ordered whether to store the key/value pairs in the order they were added it.
     */
    public JSONObject(boolean ordered) {
        if (ordered) {
            this.map = new LinkedHashMap<>();
        } else {
            this.map = new HashMap<>();
        }
    }

    /**
     * Retrieve a specific value as an Object.
     * @param key the key with which the value identifies.
     * @return the value or null.
     */
    public Object get(String key) {
        return map.get(key);
    }

    /**
     * Retrieve a specific value as a String.
     * @param key the key with which the value identifies.
     * @return the value or null.
     */
    public String getString(String key) {
        return (String) map.get(key);
    }

    /**
     * Retrieve a specific value as an Integer.
     * @param key the key with which the value identifies.
     * @return the value or null.
     */
    public Integer getInt(String key) {
        return (Integer) map.get(key);
    }

    /**
     * Retrieve a specific value as a Long.
     * @param key the key with which the value identifies.
     * @return the value or null.
     */
    public Long getLong(String key) {
        return (Long) map.get(key);
    }

    /**
     * Retrieve a specific value as a Float.
     * @param key the key with which the value identifies.
     * @return the value or .
     */
    public Float getFloat(String key) {
        return (Float) map.get(key);
    }

    /**
     * Retrieve a specific value as a Double.
     * @param key the key with which the value identifies.
     * @return the value or null.
     */
    public Double getDouble(String key) {
        return (Double) map.get(key);
    }

    /**
     * Retrieve a specific value as a JSONObject.
     * @param key the key with which the value identifies.
     * @return the value or null.
     */
    public JSONObject getObject(String key) {
        return (JSONObject) map.get(key);
    }

    /**
     * Retrieve a specific value as a JSONArray.
     * @param key the key with which the value identifies.
     * @return the value or null.
     */
    public JSONArray getArray(String key) {
        return (JSONArray) map.get(key);
    }

    /**
     * Puts a value at a specified key.
     * @param key the unique key.
     * @param value the value (must be JSON serializable).
     * @throws UnsupportedOperationException if the value is of an unsupported type (not serializable in JSON).
     */
    public void put(String key, Object value) {
        if (value != null && !Types.getClasses().contains(value.getClass())) {
            throw new UnsupportedOperationException("Cannot set \"" + key +
                    "\" as it is of type \"" + value.getClass() +
                    "\", which cannot be serialized to JSON.");
        }
        map.put(key, value);
    }

    /**
     * Puts a key/value map into the JSON Object.
     * @param range the map to be added.
     * @throws UnsupportedOperationException if the map contains unsupported values (not serializable in JSON).
     */
    public void putAll(Map<String, Object> range) {
        for (Map.Entry<String, Object> entry : range.entrySet()) {
            if (entry.getValue() != null && !Types.getClasses().contains(entry.getValue().getClass())) {
                throw new UnsupportedOperationException(
                        "Cannot set \"" + entry.getKey() + "\" as it is of type \"" +
                                entry.getValue().getClass() + "\", which cannot be serialized to JSON.");
            }
        }
        map.putAll(range);
    }

    /**
     * Puts all items in a JSONObject into this JSON Object.
     * @param range the json object to be added.
     * @throws UnsupportedOperationException if the map contains unsupported values (not serializable in JSON).
     */
    public void putAll(JSONObject range) {
        for (Map.Entry<String, Object> entry : range.getEntries()) {
            if (entry.getValue() != null && !Types.getClasses().contains(entry.getValue().getClass())) {
                throw new UnsupportedOperationException(
                        "Cannot set \"" + entry.getKey() + "\" as it is of type \"" +
                                entry.getValue().getClass() + "\", which cannot be serialized to JSON.");
            }
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Removes a key/value pair.
     * @param key the key at which to remove.
     * @return the value that was removed or null.
     */
    public Object remove(String key) {
        return map.remove(key);
    }

    /**
     * Gets the current keys in this JSON Object.
     * @return a Set of keys.
     */
    public Set<String> getKeys() {
        return map.keySet();
    }

    /**
     * Gets the values in this JSON Object.
     * @return a collection of the values.
     */
    public Collection<Object> getValues() {
        return map.values();
    }

    /**
     * Gets the entries (key/value pairs) of this JSON Object.
     * @return a Set of the entries.
     */
    public Set<Map.Entry<String, Object>> getEntries() {
        return map.entrySet();
    }

    /**
     * Gets the number of key/value pairs in this JSON Object.
     * @return the size as an int.
     */
    public int size() {
        return map.size();
    }

    @Override
    public String toString() {
        return new JSONStringifier(this).stringify();
    }
}