package com.javarush.task.task32.task3201;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) throws IOException {
        String fileName = args[0];
        int number = Integer.parseInt(args[1]);
        String text = args[2];
        RandomAccessFile random = new RandomAccessFile(new File(fileName), "rw");
        random.seek(number);
        if(random.length() - number < text.getBytes().length){
            random.seek(random.length());
        }
        random.write(text.getBytes());
        random.close();
    }
}
