package task2;

import task1.MatchingBrackets;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatchingBracketsUsingList {
    public static void main(final String[] args) {
        final Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("Enter an expression with { [ ( ) ] }: ('q' to stop): ");
            final String expression = input.nextLine();

            if (expression.trim().equalsIgnoreCase("q")) return; // Guard clause. Stop execution.

            final boolean bracketsBalanced = MatchingBracketsUsingList.checkBrackets(expression);

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
        final List<Character> expectedClosingBrackets = new ArrayList<>();
        final char[] charArray = expression.toCharArray();
        for (char c : charArray) {
            // If we see valid opening bracket.
            if (MatchingBrackets.BRACKET_PAIRS.containsKey(c)) {
                /* Then append the closing bracket to the list of expectedClosingBrackets.
                 * Note we are appending to the list rather than inserting at the front.
                 * This is much more efficient than inserting at index 0, as that requires
                 * shifting all elements.
                 */
                expectedClosingBrackets.add(MatchingBrackets.BRACKET_PAIRS.get(c));
                continue; // Skip ahead to next iteration.
            }


            /* When we see a closing bracket, then that should be at end of the list.
             * Note that we make use of short-circuiting (https://stackoverflow.com/a/8759917).
             * This is to avoid calling getLast() on an empty stack which throws a NoSuchElementException.
             */
            if (!expectedClosingBrackets.isEmpty() && expectedClosingBrackets.getLast() == c) {
                // If current character does match an expected closing bracket, then remove from end of list.
                expectedClosingBrackets.removeLast();
                continue; // Skip ahead to next iteration.
            }

            /* This is an extra check which makes the code faster.
             * Basically if variable c did not match the last element in the list of expectedClosingBrackets,
             * but is still some sort of closing bracket, then we know
             * that the string is not balanced, and we can stop checking the rest of the string.
             */
            if (MatchingBrackets.BRACKET_PAIRS.containsValue(c)) return false;
        }

        // If list is empty then string is balanced. Otherwise, string is not balanced.
        return expectedClosingBrackets.isEmpty();
    }
}
