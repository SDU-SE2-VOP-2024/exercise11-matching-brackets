package advancedtask;

import java.util.Iterator;
import java.util.Scanner;

/**
 * A wrapper around a {@link Scanner} which allows to iterate over characters in a file or other {@link java.io.InputStream}.
 * Also implements the {@link AutoCloseable} to allow usage with try-with-resource construct.
 * <p>
 * It is not expected that you are able to implement this.
 * This is merely a showcase of the more advanced aspects of Java.
 * This serves as an inspiration for those who wants to explore beyond the curriculum.
 *
 * @see Iterable
 * @see Iterator
 * @see <a href="https://stackoverflow.com/a/6863299">
 * Difference between Iterable and Iterator
 * </a>
 */
public class IterableCharScanner implements Iterable<Character>, AutoCloseable {
    // Store the backing Scanner
    private final Scanner scanner;

    public IterableCharScanner(Scanner scanner) {
        this.scanner = scanner;

        this.scanner.useDelimiter(""); // Forces to read every character.
    }


    /**
     * Part of {@link Iterable} interface.
     * It is basically a "getter" for an {@link Iterator}.
     * This allows for use in for-each loop constructs.
     *
     * @return A new {@link Iterator} for the current instance.
     */
    @Override
    public Iterator<Character> iterator() {
        // Return new Iterator for this instance.
        return this.new Itr();
    }

    /**
     * Part of {@link AutoCloseable} interface.
     * This method is automatically called by try-with-resource construct.
     * It closes the backing {@link Scanner} instance.
     */
    @Override
    public void close() {
        this.scanner.close();
    }


    /**
     * Nested inner class for Iterator.
     * This is common for {@link Iterable} implementors.
     *
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html">
     * Java Nested Classes
     * </a>
     * @see <a href="https://github.com/openjdk/jdk/blob/8a5b86c52954f6917acfda11df183691beb07f56/src/java.base/share/classes/java/util/ArrayList.java#L1029-L1074">
     * ArrayList Implementation
     * </a>
     */
    private final class Itr implements Iterator<Character> {
        @Override
        public boolean hasNext() {
            // Reference outer class' 'this' keyword.
            return IterableCharScanner.this.scanner.hasNext();
        }

        @Override
        public Character next() {
            // Reference outer class' 'this' keyword.
            return IterableCharScanner.this.scanner.next().charAt(0);
        }
    }
}
