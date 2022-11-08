package Nobilities.pali;

import java.util.List;

public class Palin {

    public static boolean stringToCheck(List<Character> somethingToCheck) {
        Character element = somethingToCheck.get(0);
        somethingToCheck.remove(0);

        return internalCheckParentheses(somethingToCheck, element);
    }

    public static boolean internalCheckParentheses(List<Character> somethingToCheck, Character previous) {
        Character element = somethingToCheck.get(0);
        somethingToCheck.remove(0);

        switch (previous) {
            case '(':
                if (element == ')') {
                    return true;
                } else {
                    return internalCheckParentheses(somethingToCheck, element);
                }

            case '[':
                if (element == ']') {
                    return true;
                } else {
                    return internalCheckParentheses(somethingToCheck, element);
                }

            case '{':
                if (element == '}') {
                    return true;
                } else {
                    return internalCheckParentheses(somethingToCheck, element);
                }

            default:
                return false;
        }
    }

}
