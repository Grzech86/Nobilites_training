package Nobilites.pali;

import Nobilities.pali.BracesPalindrome;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class BracesPalindromeTest {
    @Test
    public void testPairOfRoundBraces() {
        final String pairOfBraces = "()";
        final var result = BracesPalindrome.checkParentheses(pairOfBraces);
        Assertions.assertTrue(result);
    }

    @Test
    public void testPairOfBraces() {
        final String pairOfRoundBraces = "()";
        final var result1 = BracesPalindrome.checkParentheses(pairOfRoundBraces);
        Assertions.assertTrue(result1);

        final String pairOfSquareBraces = "[]";
        final var result2 = BracesPalindrome.checkParentheses(pairOfSquareBraces);
        Assertions.assertTrue(result2);
    }

    @Test
    public void testSymmetricalPairsOfBraces() {
        Assertions.assertTrue(BracesPalindrome.checkParentheses("()"));
        Assertions.assertTrue(BracesPalindrome.checkParentheses("[[[[[[]]]]]]"));
        Assertions.assertTrue(BracesPalindrome.checkParentheses("{([()])}"));
    }

    @Test
    public void testSymmetricalPairsOfBracesWithOtherChars() {
        Assertions.assertTrue(BracesPalindrome.checkParentheses("x{ ([()a])}"));
    }

    @Test
    public void testAsymmetricalPairsOfBracesWithOtherChars() {
        Assertions.assertTrue(BracesPalindrome.checkParentheses("()[]{}"));
        Assertions.assertTrue(BracesPalindrome.checkParentheses("({[ ]}())"));
    }



// tests of special cases

    @Test
    public void testPhraseWithoutBracesChars() {
        Assertions.assertTrue(BracesPalindrome.checkParentheses(""));
        Assertions.assertTrue(BracesPalindrome.checkParentheses("ala ma kota"));
    }
    
    @Test
    public void testIncompletePair() {
        Assertions.assertTrue(!BracesPalindrome.checkParentheses(("(")));
        Assertions.assertTrue(!BracesPalindrome.checkParentheses((")")));
        Assertions.assertTrue(!BracesPalindrome.checkParentheses(("{")));
        Assertions.assertTrue(!BracesPalindrome.checkParentheses(("}")));
        Assertions.assertTrue(!BracesPalindrome.checkParentheses(("()}")));
        Assertions.assertTrue(!BracesPalindrome.checkParentheses(("{{()}")));
    }
}


