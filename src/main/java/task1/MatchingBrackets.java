package task1;

import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

import static java.util.Map.entry;

public class MatchingBrackets {
    public static final Map<Character, Character> BRACKET_PAIRS = Map.ofEntries(
            // This is a way to create an immutable Map of key value pairs.
            entry('{', '}'),
            entry('[', ']'),
            entry('(', ')'),
            entry('<', '>')
    );

    public static void main(final String[] args) {
        final Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("Enter an expression with { [ ( ) ] }: ('q' to stop): ");
            final String expression = input.nextLine();

            if (expression.trim().equalsIgnoreCase("q")) return; // Guard clause. Stop execution.

            final boolean bracketsBalanced = MatchingBrackets.checkBrackets(expression);

            System.out.printf("'%s' has balanced brackets: %b\n", expression, bracketsBalanced);
        }
    }


    /**
     * Method to check if a {@link String} contains equal amounts of opening/closing brackets.
     *
     * @param expression The expression to check for balanced brackets.
     * @return {@code true} If there is a balanced amount brackets.
     * {@code false} If there is not a balanced amount of brackets.
     */
    public static boolean checkBrackets(final String expression) {
        final Stack<Character> expectedClosingBrackets = new Stack<>();
        final char[] charArray = expression.toCharArray();
        for (char c : charArray) {
            // If we see valid opening bracket.
            if (BRACKET_PAIRS.containsKey(c)) {
                // Then put the closing bracket into the stack.
                expectedClosingBrackets.push(BRACKET_PAIRS.get(c));
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
            if (BRACKET_PAIRS.containsValue(c)) return false;
        }

        // If stack is empty then string is balanced. Otherwise, string is not balanced.
        return expectedClosingBrackets.isEmpty();
    }
}
