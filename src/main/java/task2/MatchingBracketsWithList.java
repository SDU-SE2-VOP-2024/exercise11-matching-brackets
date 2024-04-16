package task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MatchingBracketsWithList {
    public boolean checkBrackets(String expression)
    {
        List<Character> charList = new LinkedList<>();

        char[] chars = expression.toCharArray();
        Character[] closeBracket = { '}',  ']', ')' };
        ArrayList<Character> closingBrackets = new ArrayList<>(List.of(closeBracket));
        Character[] openBracket = { '{',  '[', '(' };
        ArrayList<Character> openingBrackets = new ArrayList<>(List.of(openBracket));



        for (Character c: chars) {
            if (openingBrackets.contains(c)) {
                charList.add(0, c);
            } else if (closingBrackets.contains(c)) {
                if (charList.isEmpty()) {
                    System.out.println("Not balanced.");
                    return false;
                }
                char open = charList.remove(0);

                if((closingBrackets.indexOf(open) != openingBrackets.indexOf(c))){
                    System.out.println("Not balanced.");
                    return false;
                }
            }
        }
        return charList.isEmpty();
    }

    public static void main(String[] args) {

        MatchingBracketsWithList pc = new MatchingBracketsWithList();
        try (Scanner in = new Scanner(new File("match.txt"))){
            StringBuilder expression = new StringBuilder();
            while(in.hasNextLine()){
                expression.append(in.nextLine());
            }
            boolean b = pc.checkBrackets(expression.toString());
            System.out.println(expression + " has balanced brackets: " + b);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
