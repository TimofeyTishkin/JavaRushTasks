package com.javarush.task.task33.task3310.strategy;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class OurHashMapStorageStrategy  implements StorageStrategy{
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    private int size;
    private int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    private float loadFactor = DEFAULT_LOAD_FACTOR;

    public int hash(Long k){
        k ^= (k >>> 20) ^ (k >>> 12);
        return (int) (k ^ (k >>> 7) ^ (k >>> 4));
    }
    public int indexFor(int hash, int length){
        return hash & length-1;
    }
    public Entry getEntry(Long key){
        int hash = (key == null)? 0: hash((long) key.hashCode());
        for (Entry e = table[indexFor(hash, table.length)]; e!= null; e = e.next){
            Long k;
            if (e.hash == hash && (Objects.equals(k = e.key, key) || (key != null && key.equals(k)))) return e;
        }
        return null;
    }
    public void resize(int newCapacity){
        Entry[] oldTable = table;
        int oldCapacity = oldTable.length;

        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int)(newCapacity * loadFactor);
    }
    public void transfer(Entry[] newTable){
        Entry[] src = table;
        int newCapacity = newTable.length;
        for (int j = 0; j < src.length; j++) {
            Entry e = src[j];
            if (e != null) {
                src[j] = null;
                do {
                    Entry next = e.next;
                    int i = indexFor(e.hash, newCapacity);
                    e.next = newTable[i];
                    newTable[i] = e;
                    e = next;
                } while (e != null);

                }
            }
    }

    public void addEntry(int hash, Long key, String value, int bucketIndex){
        Entry  e = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, e);
        if (size++ >= threshold)
            resize(2 * table.length);
    }
    public void createEntry(int hash, Long key, String value, int bucketIndex){
        Entry e = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, e);
        size++;
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        if (value == null)
            return false;
        Entry[] tab = table;
        for (int i = 0; i < tab.length ; i++)
            for (Entry e = tab[i] ; e != null ; e = e.next)
            if (value.equals(e.value))
            return true;
        return false;
    }

    @Override
    public void put(Long key, String value) {
        if (key == null)
            return;
        int hash = hash((long) key.hashCode());
        int i = indexFor(hash, table.length);
        for (Entry e = table[i]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                String oldValue = e.value;
                e.value = value;

                return;
                }
            }

        addEntry(hash, key, value, i);
    }

    @Override
    public Long getKey(String value) {
        Entry[] tab = table;
        for (int i = 0; i < tab.length; i++)
            for (Entry e = tab[i]; e != null; e = e.next)
                if (e.getValue().equals(value)) return e.getKey();

        return null;
    }

    @Override
    public String getValue(Long key) {
        if (key == null)
            return null;
        int hash = hash((long)key.hashCode());
        for (Entry e = table[indexFor(hash, table.length)];
                           e != null;
        e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k)))
                return e.value;
            }
        return null;
    }

}
