package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {
    @Test
    public void testHashMapStorage(){
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();
        for(int i = 0; i < 10000; i++){
            origStrings.add(Helper.generateRandomString());
        }
        Set<Long> testLong1 = new HashSet<>();
        Set<Long> testLong2 = new HashSet<>();
        long time4first = getTimeToGetIds(shortener1, origStrings, testLong1);
        long time4second = getTimeToGetIds(shortener2, origStrings, testLong2);

        Assert.assertTrue(time4first > time4second);

        Set<Long> test4stringFirst = new HashSet<>();
        Set<Long> test4stringSecond = new HashSet<>();
        long time4testString = getTimeToGetStrings(shortener1, test4stringFirst, origStrings);
        long time4testString2 = getTimeToGetStrings(shortener2, test4stringSecond, origStrings);

        Assert.assertEquals(time4testString, time4testString2, 30);
    }
    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings){
        Date date = new Date();
        for(long long1 : ids){
            strings.add(shortener.getString(long1));
        }
        return new Date().getTime() - date.getTime();
    }
    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids){
        Date date = new Date();
        for(String string : strings){
            ids.add(shortener.getId(string));
        }
        return new Date().getTime() - date.getTime();
    }
}
