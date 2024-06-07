package com.javarush.task.task19.task1920;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        TreeMap<String, Double> dataMap = new TreeMap<>();

        while(reader.ready()){
            String richLine = reader.readLine();
            String[] dataLine = richLine.split("\\s");
            if(dataMap.containsKey(dataLine[0])){
                dataMap.put(dataLine[0],( dataMap.get(dataLine[0]) + Double.parseDouble(dataLine[1]) ));
            } else { dataMap.put(dataLine[0], Double.parseDouble(dataLine[1])); }
        }
        reader.close();

        Double maximum = 0.0;

        for(Map.Entry<String, Double> pair : dataMap.entrySet()){
            if(maximum < pair.getValue()){
                maximum = pair.getValue();
            }
        }
        TreeSet<String> maxes = new TreeSet<>();
        for(String s : dataMap.keySet()){
            if(dataMap.get(s).equals(maximum)){
                maxes.add(s);
            }
        }
        for(String s : maxes) {
            System.out.println(s);
        }
    }
}
