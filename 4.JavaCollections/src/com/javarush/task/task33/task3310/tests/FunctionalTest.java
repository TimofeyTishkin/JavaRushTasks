package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest {
    @Test
    public void testHashMapStorageStrategy(){
        Shortener shortener = new Shortener(new HashMapStorageStrategy());
        testStorage(shortener);
    }
    @Test
    public void testOurHashMapStorageStrategy(){
        Shortener shortener = new Shortener(new OurHashMapStorageStrategy());
        testStorage(shortener);
    }
    @Test
    public void testFileStorageStrategy() {
        Shortener shortener = new Shortener(new FileStorageStrategy());
        testStorage(shortener);
    }
    @Test
    public void testHashBiMapStorageStrategy(){
        Shortener shortener = new Shortener(new HashBiMapStorageStrategy());
        testStorage(shortener);
    }
    @Test
    public void testDualHashBidiMapStorageStrategy(){
        Shortener shortener = new Shortener(new DualHashBidiMapStorageStrategy());
        testStorage(shortener);
    }
    @Test
    public void testOurHashBiMapStorageStrategy(){
        Shortener shortener = new Shortener(new OurHashBiMapStorageStrategy());
        testStorage(shortener);
    }
    public void testStorage(Shortener shortener){
        String string1 = "Same String";
        String string2 = "Not The Same String";
        String string3 = "Same String";

        long long1 = shortener.getId(string1);
        long long2 = shortener.getId(string2);
        long long3 = shortener.getId(string3);

        Assert.assertNotEquals(long1, long2);
        Assert.assertNotEquals(long3, long2);

        Assert.assertEquals(long1, long3);

        String string1res = shortener.getString(long1);
        String string2res = shortener.getString(long2);
        String string3res = shortener.getString(long3);

        Assert.assertEquals(string1, string1res);
        Assert.assertEquals(string2, string2res);
        Assert.assertEquals(string3, string3res);
    }
}
