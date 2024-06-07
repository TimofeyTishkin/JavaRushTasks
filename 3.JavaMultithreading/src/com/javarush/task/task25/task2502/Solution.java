package com.javarush.task.task25.task2502;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() {
            //init wheels here
            wheels = new ArrayList<>();
            String[] names = loadWheelNamesFromDB();
            try{
                if(names.length != 4) throw new NullPointerException();
                for(String name : names){
                    try{
                        wheels.add(Wheel.valueOf(name));
                    } catch (Exception e){
                        throw new NullPointerException();
                    }
                }
            } catch (Exception e){
                throw new NullPointerException();
            }
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
    }
}
