package com.javarush.task.task20.task2021;



import java.io.*;

public class Solution implements Serializable {

    public static class SubSolution extends Solution {
        private void writeObject(java.io.ObjectOutputStream out) throws NotSerializableException {
            throw new NotSerializableException();
        }
        private void readObject(java.io.ObjectInputStream in) throws NotSerializableException {
            throw new NotSerializableException();
        }
    }

    public static void main(String[] args) {

    }

}
