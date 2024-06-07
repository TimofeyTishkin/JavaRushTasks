package com.javarush.task.task32.task3202;

import java.io.*;

/* 
Читаем из потока
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("testFile.log"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) {
        if(is == null) return new StringWriter();
        try {
            StringWriter writer = new StringWriter(is.available());
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            while (reader.ready()) {
                writer.write(reader.readLine());
            }
            return writer;
        } catch (IOException e){
            return getAllDataFromInputStream(is);
        }
    }
}