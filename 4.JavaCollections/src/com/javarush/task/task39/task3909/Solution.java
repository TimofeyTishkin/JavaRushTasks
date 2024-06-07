package com.javarush.task.task39.task3909;

/* 
Одно изменение
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isOneEditAway("am", "mak"));
        System.out.println(isOneEditAway("aline", "line"));
        System.out.println(isOneEditAway("line", "liene"));
        System.out.println(isOneEditAway("line", "linse"));
        System.out.println(isOneEditAway("line", "linsess"));
        System.out.println(isOneEditAway("01", "102"));
        System.out.println(isOneEditAway("1032", "102"));
        System.out.println(isOneEditAway("123", "1023"));
    }

    public static boolean isOneEditAway(String first, String second) {
        // особые случаи
        if (first == null || second == null)
            return false;

        if (first.length() == 0 && second.length() == 1
                || first.length() == 1 && second.length() == 0
                || first.length() == 0 && second.length() == 0)
            return true;

        boolean result = false;

        // первое длиннее второго на 1 char
        if (first.length() - second.length() == 1) {

            for (int i = 0; i < first.length(); i++) {
                if ((first.substring(0, i) + first.substring(i + 1)).equals(second)) {
                    result = true;
                    break;
                }
            }

            //второе длиннее первого на 1 char
        } else if (second.length() - first.length() == 1) {

            for (int i = 0; i < second.length(); i++) {
                if ((second.substring(0, i) + second.substring(i + 1)).equals(first)) {
                    result = true;
                    break;
                }
            }
            // длинна одинаковая
        } else if (first.length() == second.length()) {
            char[] firstChars = first.toCharArray();
            char[] secondChars = second.toCharArray();
            int i = 0;

            for (; i < first.length(); i++) {
                result = true;

                if (firstChars[i] != secondChars[i]) {
                    i++;

                    for (; i < first.length(); i++) {

                        if (firstChars[i] != secondChars[i]) {
                            result = false;
                            break;
                        }
                    }
                    break;
                }
            }
        }
        return result;
    }
}
