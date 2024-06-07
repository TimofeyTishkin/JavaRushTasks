package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    public String getParent(String s) {

        for(Entry<String> string : this.treeList){
            if(s.equals(string.elementName)){
                return string.parent.elementName;
            }
        }
        return null;
    }
    private List<Entry<String>> treeList = new ArrayList<>();

    static class Entry<T> implements Serializable{
        String elementName;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren|availableToAddRightChildren;
        }
    }
    Entry<String> root;

    public CustomTree() {
        root = new Entry<>("root");
        root.parent = null;
        this.treeList.add(root);
    }

    @Override
    public int size() {
        return treeList.size() - 1;
    }

    @Override
    public boolean add(String s) {
        Entry<String> newEntry = new Entry<>(s);
        for(int i = 0; i < treeList.size(); i++){
            Entry<String> entry = treeList.get(i);
            if(s.equals(entry.elementName)) return false;
            if(!entry.isAvailableToAddChildren()) continue;
            if(entry.availableToAddLeftChildren){
                newEntry.parent = entry;
                entry.leftChild = newEntry;
                entry.availableToAddLeftChildren = false;
                treeList.add(newEntry);
                return true;
            }
            if(entry.availableToAddRightChildren){
                newEntry.parent = entry;
                entry.rightChild = newEntry;
                entry.availableToAddRightChildren = false;
                treeList.add(newEntry);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if(!(o instanceof String)) throw new UnsupportedOperationException();
        for(Entry<String> entry : treeList){
            if(entry.elementName.equals(o)){
                treeList.remove(entry);
                if(entry.leftChild != null) {
                    remove(entry.leftChild.elementName);
                }
                if(entry.rightChild!= null){
                    remove(entry.rightChild.elementName);

                }
                if(entry.parent.rightChild == entry){
                    entry.parent.rightChild = null;
                }
                if(entry.parent.leftChild == entry){
                    entry.parent.leftChild = null;
                }
                if(entry.parent.rightChild == null && entry.parent.leftChild == null) {
                    entry.parent.availableToAddLeftChildren = true;
                    entry.parent.availableToAddRightChildren = true;
                }
                return true;
            }
        }
        return false;
    }

    public String get(int index) {
        throw new UnsupportedOperationException();
    }
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }
}
