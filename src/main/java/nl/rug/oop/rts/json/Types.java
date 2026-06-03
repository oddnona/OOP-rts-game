package nl.rug.oop.rts.json;

import java.util.ArrayList;
import java.util.List;

/**
 * The allowed JSON types.
 */
public enum Types {

    BOOLEAN(Boolean.class),
    STRING(String.class),
    INT(Integer.class),
    LONG(Long.class),
    FLOAT(Float.class),
    DOUBLE(Double.class),
    OBJECT(JSONObject.class),
    ARRAY(JSONArray.class);

    /**
     * The Java class associated with the JSON type.
     */
    private final Class<?> clazz;

    /**
     * Constructs a {@code Types} enum constant with the associated class type.
     *
     * @param clazz the class corresponding to this JSON type
     */
    Types(Class<?> clazz) {
        this.clazz = clazz;
    }

    /**
     * Returns a list of all allowed JSON type classes.
     *
     * @return a list of {@link Class} objects
     */
    public static List<Class<?>> getClasses() {
        List<Class<?>> list = new ArrayList<>();
        for (Types type : values()) {
            list.add(type.clazz);
        }
        return list;
    }
}