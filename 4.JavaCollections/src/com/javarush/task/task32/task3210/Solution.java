package com.javarush.task.task32.task3210;

/* 
Используем RandomAccessFile
*/

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Solution {
    public static void main(String... args) throws IOException {
            int number = Integer.parseInt(args[1]);
            String text = args[2];
            RandomAccessFile randomAccessFile = new RandomAccessFile(args[0], "rw");
            randomAccessFile.seek(number);
            byte[] data = new byte[text.length()];
                randomAccessFile.read(data, 0, text.length());
            String inFile = new String(data);
            String trueOrFalse = text.equals(inFile) ? "true" : "false";
            randomAccessFile.seek(randomAccessFile.length());
            randomAccessFile.write(trueOrFalse.getBytes());
            randomAccessFile.close();
    }

}
