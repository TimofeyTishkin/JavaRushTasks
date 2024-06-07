package com.javarush.task.task15.task1522;

/* 
Закрепляем паттерн Singleton
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) {

    }

    public static Planet thePlanet;
static {
    try {
        readKeyFromConsoleAndInitPlanet();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    //add static block here - добавьте статический блок тут

    public static void readKeyFromConsoleAndInitPlanet() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String yes = reader.readLine();
        if (yes.equals("earth")||yes.equals("sun")||yes.equals("moon")) {
            if (yes.equals("earth")) {
                thePlanet = Earth.getInstance();
            }

            if (yes.equals("moon")) {
                thePlanet = Moon.getInstance();
            }
            if (yes.equals("sun")) {
                thePlanet = Sun.getInstance();
            }
        }
        else thePlanet = null;
        // implement step #5 here - реализуйте задание №5 тут
    }
}
