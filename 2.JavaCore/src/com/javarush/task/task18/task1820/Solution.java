package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/

import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);

        String file1= scanner.nextLine();
        String file2=scanner.nextLine();
        scanner.close();

        scanner = new Scanner(new FileReader(file1));
        FileWriter writer = new FileWriter(file2);

        while (scanner.hasNextLine()){
            String s = scanner.nextLine();
            bbf(s,writer);
        }
        scanner.close();
        writer.close();
    }

    public static void bbf(String a,FileWriter writer) throws IOException {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            if (' ' == (a.charAt(i)) || i == a.length() - 1) {
                if (' ' == (a.charAt(i)) == false) s.append(a.charAt(i));
                writer.write(Math.round(Double.parseDouble(s.toString())) + " ");
                s = new StringBuilder();
            } else s.append(a.charAt(i));
        }
    }
}
