package nl.rug.oop.rts.json;

import java.util.Collection;
import java.util.Map.Entry;

/**
 * A utility class that converts {@link JSONObject} or {@link JSONArray} into their JSON string representation.
 * <p>
 * This class performs manual stringification of JSON-compatible data types such as strings, numbers, booleans,
 * arrays, and objects. It also handles escaping of special characters and Unicode sequences.
 */
public class JSONStringifier {
    /**
     * The main container that holds all JSON data (JSONObject or JSONArray only).
     */
    private final Object container;

    /**
     * Instantiates a new JSONStringifier.
     * @param container the main container (a {@link JSONArray} or {@link JSONObject}).
     * @throws IllegalArgumentException if the container is not a {@link JSONArray} or {@link JSONObject}.
     */
    protected JSONStringifier(Object container) {
        // check if the container is valid
        if (!(container instanceof JSONObject) && !(container instanceof JSONArray)) {
            throw new IllegalArgumentException("Cannot stringify a non-json object. Use JSONObject or JSONArray.");
        }
        this.container = container;
    }

    /**
     * Stringify the container.
     * @return the stringified JSON
     */
    public String stringify() {
        return stringify(container, 0);
    }

    /**
     * Stringifies any type of valid JSON-serializable value.
     * @param object the value to stringify.
     * @param indentLevel the indentation level
     * @return the stringified value.
     */
    private String stringify(Object object, int indentLevel) {
        if (object == null) {
            return "null";
        } else if (object instanceof Boolean b) {
            return b ? "true" : "false";
        } else if (object instanceof String s) {
            return stringifyString(s);
        } else if (object instanceof Number n) {
            return String.valueOf(n);
        } else if (object instanceof JSONObject o) {
            return stringifyObject(o, indentLevel);
        } else if (object instanceof JSONArray a) {
            return stringifyArray(a, indentLevel);
        }
        return null;
    }

    /**
     * Stringifies a boolean.
     * @param bool the boolean to stringify.
     * @return the stringified boolean.
     */
    private String stringifyBoolean(Boolean bool) {
        return bool ? "true" : "false";
    }

    /**
     * Stringifies null.
     * @return the stringified null.
     */
    private String stringifyNull() {
        return "null";
    }

    /**
     * Stringifies a string (adds the quotes and encodes characters that need escaping and Unicode characters).
     * @param string the string to stringify.
     * @return the stringified string.
     */
    private String stringifyString(String string) {
        StringBuilder sb = new StringBuilder();
        // prepend the first quote
        sb.append('"');
        for (char c : string.toCharArray()) {
            switch (c) {
                // escape double quotes
                case '"': sb.append("\\\"");
                    break;
                // escape backslash and other printer chars
                case '\\': sb.append("\\\\");
                    break;
                case '\b': sb.append("\\b");
                    break;
                case '\f': sb.append("\\f");
                    break;
                case '\n': sb.append("\\n");
                    break;
                case '\r': sb.append("\\r");
                    break;
                case '\t': sb.append("\\t");
                    break;
                default:
                    // escape out-of-range Unicode characters
                    if (c < 0x20 || c > 0x7E) {
                        sb.append(String.format("\\u%04X", (int) c));
                    }else {
                        sb.append(c);
                    }
            }
        }
        // append last quote
        sb.append('"');
        return sb.toString();
    }

    /**
     * Stringifies a number.
     * @param number the number to stringify.
     * @return the string value of the number.
     */
    private String stringifyNumber(Number number) {
        return String.valueOf(number);
    }

    /**
     * Stringifies a JSON Object.
     * @param object the object to stringify.
     * @param indentLevel the indentation level
     * @return the stringified object.
     */
    private String stringifyObject(JSONObject object, int indentLevel) {
        StringBuilder builder = new StringBuilder();
        // prepend opening curly bracket
        builder.append("{\n");

        // get all entries in the object
        Collection<Entry<String, Object>> entries = object.getEntries();
        // keep an index for the last comma check
        int i = 0;
        for (Entry<String, Object> entry : entries) {
            // append "<key>":<value>
            builder.append("\t".repeat(indentLevel + 1));
            builder.append(stringifyString(entry.getKey())).append(": ");
            builder.append(stringify(entry.getValue(), indentLevel + 1));

            // add tailing "," for all items but the last one
            if (++i != entries.size()) {
                builder.append(",");
            }
            builder.append("\n");
        }
        // append closing bracket
        builder.append("\t".repeat(indentLevel)).append("}");
        return builder.toString();
    }

    /**
     * Stringifies a JSON Array.
     * @param array the array to stringify.
     * @param indentLevel the indentation level
     * @return the stringified array.
     */
    private String stringifyArray(JSONArray array, int indentLevel) {
        StringBuilder builder = new StringBuilder();
        // prepend opening square bracket
        builder.append("[\n");

        // iterate all items
        int i = 0;
        for (Object item : array.getItems()) {
            builder.append("\t".repeat(indentLevel + 1));
            builder.append(stringify(item, indentLevel + 1));
            // add tailing "," for all items but the last one
            if (++i != array.getItems().size()) {
                builder.append(",");
            }
            builder.append("\n");
        }
        // append closing bracket
        builder.append("\t".repeat(indentLevel)).append("]");
        return builder.toString();
    }
}
