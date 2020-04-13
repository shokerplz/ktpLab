package org.example;

public class Palindrome {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            String result = isPalindrome(s) ? " является палиндромом" : " не является палиндромом";
            System.out.println("Строка: "+s+result);
        } }
    public static boolean isPalindrome(String s) {
        return(s.equals(reverseString(s)));
    }
    public static String reverseString(String inputStr) {
        String outStr = "";
        int inputStrLen = inputStr.length();
        for (int i = 1; i <= inputStrLen; i++) {
            outStr += inputStr.charAt(inputStrLen - i);
        }
        return(outStr);
    }
}
