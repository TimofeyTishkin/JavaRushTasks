package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Solution {

    public static Map<String, String> runtimeStorage = new HashMap<>();

    public static void save(OutputStream outputStream) throws Exception {
        Properties prop = new Properties();

        for ( Map.Entry<String, String> pair : runtimeStorage.entrySet())   {
            prop.setProperty(pair.getKey(), pair.getValue());
        }

        prop.store(outputStream, "jebi ga");
        //напишите тут ваш код
    }

    public static void load(InputStream inputStream) throws IOException {
        Properties prop = new Properties();
        prop.load(inputStream);
        Set<String> keys = prop.stringPropertyNames();
        for ( String key : keys )   {
            runtimeStorage.put(key, prop.getProperty(key));
        }//напишите тут ваш код
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             FileInputStream fos = new FileInputStream(reader.readLine())) {
            load(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(runtimeStorage);
    }
}
