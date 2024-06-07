package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(75);
    }

    public void createExpression(int number) {
        ArrayList<Character> znPrep = new ArrayList<>();
        int myNumber = number;
        int ost;
        ArrayList<Integer> queue = new ArrayList<>();
        while (myNumber != 0) {
            if (myNumber > 2) {
                ost = myNumber % 3;
                myNumber /= 3;
                queue.add(ost);
                if (ost == 2) myNumber++;
            } else {
                queue.add(myNumber);
                myNumber = 0;
            }
        }
        Collections.reverse(queue);
        int index = 0;
        if (queue.get(0) == 2) {
            znPrep.add('+');
            znPrep.add('-');
            index = 1;
        }

        for (int i = index; i < queue.size(); i++) {
            if (queue.get(i) == 2) {
                znPrep.add('-');
            }
            if (queue.get(i) == 1) {
                znPrep.add('+');
            }
            if (queue.get(i) == 0) {
                znPrep.add('0');
            }
        }
        Collections.reverse(znPrep);
        StringBuilder sb = new StringBuilder(String.valueOf(number) + " =");
        for (int i = 0; i < znPrep.size(); i++) {
            if (znPrep.get(i) != '0') {
                sb.append(" ").append(znPrep.get(i)).append(" ").append(pow(i));
            }
        }
        System.out.println(sb.toString());
    }

    private int pow(int pow) {
        if (pow == 0) return 1;
        if (pow == 1) return 3;
        int result = 3;
        for (int i = 2; i <= pow; i ++) {
            result = result * 3;
        }
        return result;
    }
}