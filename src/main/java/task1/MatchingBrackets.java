package task1;

import java.util.Scanner;
import java.util.Stack;

public class MatchingBrackets {

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
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
