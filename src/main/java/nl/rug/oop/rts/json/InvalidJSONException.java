package nl.rug.oop.rts.json;

/**
 * Exception thrown when a JSON parsing error occurs.
 */
public class InvalidJSONException extends RuntimeException {

    /**
     * Constructs a new InvalidJSONException with the specified detail message.
     *
     * @param message the detail message explaining the cause of the exception
     */
    public InvalidJSONException(String message) {
        super(message);
    }
}
