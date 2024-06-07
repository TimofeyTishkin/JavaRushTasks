package com.javarush.task.task14.task1420;

/* 
НОД
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            int firstNumber = Integer.parseInt(reader.readLine());
            int secondNumber = Integer.parseInt(reader.readLine());
            if(firstNumber > 0 && secondNumber >0) System.out.println(NOD(firstNumber, secondNumber));
            else throw new Exception();
    }
    public static int NOD(int a, int b){
        return b != 0 ? NOD(b, a%b) : a;
    }
}
