package nl.rug.oop.rts.json;

import java.util.Collection;
import java.util.Map;

/**
 * Utility class for parsing and creating JSON objects and arrays.
 * <p>
 * Provides static methods to convert {@link Map} and {@link Collection} into {@link JSONObject} and
 * {@link JSONArray} respectively.
 */
public class JSON {
    /**
     * Creates a {@link JSONArray} from a {@link Collection}.
     * <br/>
     * All elements in the collection need to be JSON-serializable
     * @param collection the collection to convert
     * @return the JSONArray
     */
    public static JSONArray fromCollection(Collection<Object> collection) {
        JSONArray array = new JSONArray();
        array.addAll(collection);

        return array;
    }

    /**
     * Creates a {@link JSONObject} from a {@link Map}.
     * <br/>
     * All keys need to be strings.<br/>
     * All values in the map need to be JSON-serializable.
     * @param map the map to convert
     * @return the JSONObject
     */
    public static JSONObject fromMap(Map<String, Object> map) {
        JSONObject object = new JSONObject();
        object.putAll(map);

        return object;
    }
}