package com.javarush.task.task32.task3213;

import java.io.IOException;
import java.io.StringReader;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
                                                    // Khoor#Dpljr#&C,₷B'3
    }

    public static String decode(StringReader reader, int key) throws IOException {
        if (reader != null) {
            StringBuilder builder = new StringBuilder();
            char[] buff = new char[reader.toString().length()];
            int count;
            if ((count = reader.read(buff)) != -1) {
                for (int i = 0; i < count; i++) {
                    buff[i] += (char) key;
                    builder.append((char)((int)buff[i]));
                }
            }
            return builder.toString();
        }
        return "";
    }
}
