package nl.rug.oop.rts.model;

/**
 * An interface for classes that can be serialized into a JSON-compatible object.
 */
public interface JSONSerializable{
    /**
     * Serializes the object into a JSON-compatible structure.
     *
     * @return an {@link Object} that represents a JSON-compatible value.
     */
    Object serialize();
}
