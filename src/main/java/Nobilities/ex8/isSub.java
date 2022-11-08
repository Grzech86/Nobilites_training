package Nobilities.ex8;

//String Rotation: Assume you have a method isSubstring which checks if one word is a substring of another.
//Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using only one call to isSubstring
// (e.g., "waterbottle" is a rotation of"erbottlewat").


import java.util.LinkedList;
import java.util.Queue;

public class isSub {
    static boolean check_rotation(String s, String goal)
    {
        if (s.length() != goal.length())
            ;
        Queue<Character> q1 = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            q1.add(s.charAt(i));
        }
        Queue<Character> q2 = new LinkedList<>();
        for (int i = 0; i < goal.length(); i++) {
            q2.add(goal.charAt(i));
        }
        int k = goal.length();
        while (k>0) {
            k--;
            char ch = q2.peek();
            q2.remove();
            q2.add(ch);
            if (q2.equals(q1))
                return true;
        }
        return false;
    }
    public static void main(String[] args)
    {
        String s1 = "ABCD";
        String s2 = "CDAB";
        if (check_rotation(s1, s2))
            System.out.print(s2+ " is a rotated form of " +  s1
                    +"\n");
        else
            System.out.print(s2+ " is not a rotated form of " +  s1
                    +"\n");
        String s3 = "ACBD";
        if (check_rotation(s1, s3))
            System.out.print(s3+ " is a rotated form of " +  s1
                    +"\n");
        else
            System.out.print(s3+ " is not a rotated form of " +  s1
                    +"\n");
    }
}

class StringRotationV2
{
    /* Function checks if passed strings (str1 and str2)
       are rotations of each other */
    static boolean areRotations(String str1, String str2)
    {
        // There lengths must be same and str2 must be
        // a substring of str1 concatenated with str1.
        return (str1.indexOf(str1)==str2.indexOf(str2));
    }

    // Driver method
    public static void main (String[] args)
    {
        String str1 = "water";
        String str2 = "Waterom";

        if (areRotations(str1, str2))
            System.out.println("TRUE");
        else
            System.out.printf("FALSE");
    }
}

class StringRotation
{
    /* Function checks if passed strings (str1 and str2)
       are rotations of each other */
    static boolean areRotations(String str1, String str2)
    {
        // There lengths must be same and str2 must be
        // a substring of str1 concatenated with str1.
        return (str1.length() == str2.length()) &&
                ((str1 + str1).indexOf(str2) != -1);
    }

    // Driver method
    public static void main (String[] args)
    {
        String str1 = "AACD";
        String str2 = "ACDA";

        if (areRotations(str1, str2))
            System.out.println("Strings are rotations of each other");
        else
            System.out.printf("Strings are not rotations of each other");
    }
}
// This code is contributed by  munjal
