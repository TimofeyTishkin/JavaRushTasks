package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.*;

/* 
Нити и байты
*/

public class Solution {
    public static volatile Map<String, Integer> resultMap = new HashMap<>();

    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            while(true){
                String newFileName = reader.readLine();
                if(newFileName.equals("exit")) throw new IOException();
                Thread thread = new ReadThread(newFileName);
                thread.start();
            }
        } catch (IOException e){
            System.out.println("wrong");
        }

    }

    public static class ReadThread extends Thread {
        protected  String filename;
        public ReadThread(String fileName) {
            super(fileName);
            this.filename = fileName;
        }
        @Override
        public void run() {
            try(FileInputStream fileInputStream = new FileInputStream(filename)){
                HashMap<Integer, Integer> map = new HashMap<>();
                int count = 1;
                while(fileInputStream.available()>0) {
                    int b = fileInputStream.read();
                    if (map.containsKey(b)) {
                        count=map.get(b)+1;
                        map.put(b, count);
                    }
                    else {
                        map.put(b, 1);
                    }
                }
                Map.Entry<Integer, Integer> max = null;
                for(Map.Entry<Integer, Integer> pair : map.entrySet()){
                    if(max != null){
                        if(max.getValue() < pair.getValue()) max = pair;
                    }else{
                        max = pair;
                    }
                }
                assert max != null;
                resultMap.put(filename, max.getKey());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // implement file reading here - реализуйте чтение из файла тут
    }
}
