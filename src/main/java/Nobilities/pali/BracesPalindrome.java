package Nobilities.pali;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BracesPalindrome {

//    public static Boolean checkParentheses(String pairOfBraces) {
//        char[] phraseWithBracesTable = pairOfBraces.toCharArray();
//        return (phraseWithBracesTable[0] == '(') && (phraseWithBracesTable[1] == ')');
//    }
//---------------------------------------------------------------------------------------------------------------------
//    public static Boolean checkParentheses(String pairOfBraces) {
//        Map<Character, Character> bracesMap = new HashMap<>(Map.of('(',')','[',']','{','}' ));
//        char[] phraseWithBracesTable = pairOfBraces.toCharArray();
//        final var bracesKey = phraseWithBracesTable[0];
//        return (bracesMap.containsKey(bracesKey)) && (phraseWithBracesTable[1] == bracesMap.get(bracesKey));
//    }
//---------------------------------------------------------------------------------------------------------------------
//    public static Boolean checkParentheses(String phraseWithBraces) {
//        Map<Character, Character> bracesMap = new HashMap<>(Map.of('(', ')', '[', ']', '{', '}'));
//        char[] phraseWithBracesTable = phraseWithBraces.toCharArray();
//        if ((phraseWithBracesTable.length % 2) > 0) {
//            return false;
//        }
//        for (int i = 0; i < phraseWithBraces.length() / 2; i++) {
//            var currentChar = phraseWithBracesTable[i];
//            if (bracesMap.get(currentChar) != phraseWithBracesTable[phraseWithBracesTable.length - i - 1]) {
//                return false;
//            }
//        }
//        return true;
//    }
//---------------------------------------------------------------------------------------------------------------------
    public static Boolean checkParentheses(String phraseWithBraces) {
        Map<Character, Character> bracesMap = new HashMap<>(Map.of('(', ')', '[', ']', '{', '}'));
        char[] phraseWithBracesAsCharsTable = phraseWithBraces.toCharArray();
        AuxiliaryStack auxiliaryStack = new AuxiliaryStack();

        for (int i = 0; i < phraseWithBracesAsCharsTable.length; i++) {
            var currentChar = phraseWithBracesAsCharsTable[i];
            if (bracesMap.containsKey(currentChar)) {
                auxiliaryStack.addNextToStack(currentChar);
            } else if ( ( auxiliaryStack.getStackSize() > 0 ) &&  (currentChar == bracesMap.get(auxiliaryStack.getLastOpenBraces() ) ) ){
                auxiliaryStack.removeLastFromStack();
            } else if ( bracesMap.containsValue(currentChar) ) {
                return false;
            }
        }
        return (auxiliaryStack.getStackSize() <= 0);
    }

    public static void main(String[] args) {

    }
}

class AuxiliaryStack {
    private List<Character> stackOfBraces = new ArrayList<>();
    private int stackSize = 0;
    private char emptyChar;
    private char lastOpenBraces;

    public char getLastOpenBraces() {
        return lastOpenBraces;
    }

    public int getStackSize() {
        return stackSize;
    }

    public void addNextToStack(Character bracesSign) {
        stackOfBraces.add(bracesSign);
        stackSize++;
        lastOpenBraces = bracesSign;
    }

    public void removeLastFromStack() {
        stackOfBraces.remove(stackSize - 1);
        stackSize--;
        lastOpenBraces = (stackSize == 0 ? emptyChar : stackOfBraces.get(stackSize - 1) );
    }


}

