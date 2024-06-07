package com.javarush.task.task12.task1226;

/* 
Лазать, летать и бегать
*/

public class Solution {
    public interface CanFly{
        void fly();
    }
    public interface CanClimb{
        void climb();
    }
    public interface CanRun{
        void run();
    }
    public static void main(String[] args) {

    }

    public class Cat implements CanRun, CanClimb {
        public void run(){
            System.out.println("it runs");
        }
        public void climb(){
            System.out.println("it climbs");
        }
    }

    public class Dog implements CanRun {
        public void run(){
            System.out.println("it runs");
        }
    }

    public class Tiger extends Cat {

    }

    public class Duck implements  CanRun, CanFly{
        public void run(){
            System.out.println("it runs");
        }
        public void fly(){
            System.out.println("it flies");
        }
    }
}
