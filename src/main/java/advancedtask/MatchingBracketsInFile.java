package advancedtask;

import task1.MatchingBrackets;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/**
 * This is an advanced implementation of Matching brackets in file.
 * It uses an iterator approach to avoid loading the whole file into memory.
 * <p>
 * It is not expected that you are able to implement this.
 * This is merely a showcase of the more advanced aspects of Java.
 * This serves as an inspiration for those who wants to explore beyond the curriculum.
 *
 * @see MatchingBrackets
 * @see IterableCharScanner
 */
public class MatchingBracketsInFile {

    public static void main(final String[] args) {
        final Scanner input = new Scanner(System.in);

        // See https://stackoverflow.com/a/7603444 on getting current working directory.
        System.out.printf("Current directory: '%s'\n", System.getProperty("user.dir"));

        while (true) {
            System.out.print("Enter path to file (Can be relative to current directory) ('q' to quit): ");
            final String fileName = input.nextLine();

            if (fileName.trim().equalsIgnoreCase("q")) return; // Guard clause. Stop execution.

            final File file = new File(fileName);

            if (!file.exists()) {
                System.out.printf("File '%s' not found!\nTry again\n", fileName);
                continue; // Skip ahead to next iteration.
            }

            if (file.isDirectory()) {
                System.out.printf("File '%s' is a directory!\nTry again\n", fileName);
                continue; // Skip ahead to next iteration.
            }

            /* Using try-with-resource, that way we do not have to remember to close file.
             * Using the custom IterableCharScanner to avoid loading the whole file into memory.
             * This is however not a requirement for your implementation.
             */
            try (final IterableCharScanner scanner = new IterableCharScanner(new Scanner(file))) {
                // The checkBrackets method has been changed to accept an Iterable instead of a String.
                final boolean bracketsBalanced = MatchingBracketsInFile.checkBrackets(scanner);

                System.out.printf("File '%s' has balanced brackets: %b\n", fileName, bracketsBalanced);
            } catch (FileNotFoundException e) {
                // In reality will this never happen, as we already check if file exits.
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * Method to check if a {@link String} contains equal amounts of opening/closing brackets.
     *
     * @param characterSequence A sequence of characters to check for balanced brackets.
     * @return {@code true} If there is a balanced amount brackets.
     * {@code false} If there is not a balanced amount of brackets.
     */
    public static boolean checkBrackets(final Iterable<Character> characterSequence) {
        final Stack<Character> expectedClosingBrackets = new Stack<>();
        for (char c : characterSequence) {
            // If we see valid opening bracket.
            if (MatchingBrackets.BRACKET_PAIRS.containsKey(c)) {
                // Then put the closing bracket into the stack.
                expectedClosingBrackets.push(MatchingBrackets.BRACKET_PAIRS.get(c));
                continue; // Skip ahead to next iteration.
            }

            /* When we see a closing bracket, then that should be at top of the stack.
             * Note that we make use of short-circuiting (https://stackoverflow.com/a/8759917).
             * This is to avoid calling peek() on an empty stack which throws an EmptyStackException.
             */
            if (!expectedClosingBrackets.isEmpty() && c == expectedClosingBrackets.peek()) {
                // If current character does match an expected closing bracket, then pop of the stack.
                expectedClosingBrackets.pop();
                continue; // Skip ahead to next iteration.
            }

            /* This is an extra check which makes the code faster.
             * Basically if variable c did not match the top of the stack,
             * but is still some sort of closing bracket, then we know
             * that the string is not balanced, and we can stop checking
             * the rest of the string.
             */
            if (MatchingBrackets.BRACKET_PAIRS.containsValue(c)) return false;
        }

        // If stack is empty then string is balanced. Otherwise, string is not balanced.
        return expectedClosingBrackets.isEmpty();
    }
}
