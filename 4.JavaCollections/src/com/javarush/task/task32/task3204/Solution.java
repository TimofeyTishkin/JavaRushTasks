package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) throws IOException{
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() throws IOException {
        char[] alphabetOriginal = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        Collections.shuffle(Arrays.asList(alphabet));
        ByteArrayOutputStream password = new ByteArrayOutputStream();
        boolean isItLower = Math.random() < 0.5;
        while(password.toByteArray().length < 8){
            isItLower = !isItLower;
            char rng = alphabet[(int)(Math.random()*26)];
            String toRng = isItLower? String.valueOf(rng).toLowerCase():String.valueOf(rng).toUpperCase();

            password.write(toRng.getBytes());

            if(password.toByteArray().length < 8){
                int add = new String(alphabetOriginal).indexOf(rng);
                if(String.valueOf(add).length() == 1)
                password.write(String.valueOf(add).getBytes());
                else password.write(String.valueOf(add).substring(0,1).getBytes());

            }
        }

        return password;
    }
}