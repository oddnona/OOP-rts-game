package nl.rug.oop.rts.json;

import java.io.IOException;
import java.io.Reader;

/**
 * A utility class that acts as a stream using a Reader for characters.
 */
public class CharStream {
    /**
     * The reader to use.
     */
    private final Reader reader;
    /**
     * The current character ( 2 = uninitialized, -1 = EOF).
     */
    private int current = -2;

    /**
     * Instantiates a CharStream given a reader.
     * @param reader the reader to create the stream from.
     */
    public CharStream(Reader reader) {
        this.reader = reader;
    }

    /**
     * Looks ahead into the reader at the next char without changing the cursor.
     * @return the next character
     * @throws IOException if an I/ O error occurs
     */
    public int peek() throws IOException {
        if (current == -2){
            current = reader.read();
        }
        return current;
    }

    /**
     * Looks at the next character and moves the cursor by one to the right.
     * @return the next character.
     * @throws IOException if an I/ O error occurs.
     */
    public int next() throws IOException {
        int result = peek();
        current = reader.read();
        return result;
    }

    /**
     * Skips (by moving the cursor over) the white space character.
     * @throws IOException if an I/ O error occurs
     */
    public void skipWhitespace() throws IOException {
        while (Character.isWhitespace(peek())){
            next();
        }
    }
}

