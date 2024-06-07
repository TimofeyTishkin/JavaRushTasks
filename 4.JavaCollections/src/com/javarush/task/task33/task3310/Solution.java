package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.FileStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.OurHashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        testStrategy(new OurHashMapStorageStrategy(), 10000);
        testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new FileStorageStrategy(), 100);
    }
    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set<Long> result = new LinkedHashSet<>();
        for(String string : strings){
            result.add(shortener.getId(string));
        }
        return result;
    }
    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set<String> result = new LinkedHashSet<>();
        for(Long key : keys){
            result.add(shortener.getString(key));
        }
        return result;
    }
    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        System.out.println(strategy.getClass().getSimpleName());
        Set<String> testStrings = new HashSet<>((int) elementsNumber);
        for(int i = 0; i < elementsNumber; i++){
            testStrings.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);

        Date dateS = new Date();
        Set<Long> ides = getIds(shortener, testStrings);
        Date dateE = new Date();
        System.out.println(dateE.getTime() - dateS.getTime() + "ms");

        Date dateS2 = new Date();
        Set<String> strings = getStrings(shortener, ides);
        Date dateE2 = new Date();
        System.out.println(dateE2.getTime() - dateS2.getTime() + "ms");

        if(testStrings.equals(strings))
        System.out.println("Тест пройден.");
        else System.out.println("Тест не пройден.");
    }
}
