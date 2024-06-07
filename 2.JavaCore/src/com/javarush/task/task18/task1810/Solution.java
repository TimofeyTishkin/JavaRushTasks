package com.javarush.task.task18.task1810;

/* 
DownloadException
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws DownloadException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for(;;){
            String fileName;
            FileInputStream File;
            try {
                fileName = reader.readLine();
                File = new FileInputStream(fileName);
                if(File.available() < 1000){
                    File.close();
                    throw new DownloadException();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static class DownloadException extends Exception {

    }
}
