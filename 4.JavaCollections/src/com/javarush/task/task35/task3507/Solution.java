package com.javarush.task.task35.task3507;

import java.io.*;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> allAnimals = new HashSet<>();
        MyClassLoader classLoader = new MyClassLoader();

        try {
            for (File file : Objects.requireNonNull(new File(pathToAnimals).listFiles())) {
                Class<?> clazz = classLoader.loadClaZZ(file.getAbsolutePath());
                if (Animal.class.isAssignableFrom(clazz) && clazz.getConstructors().length != 0)
                    allAnimals.add((Animal) clazz.newInstance());
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        return allAnimals;
    }
    public static class MyClassLoader extends ClassLoader {
        public Class<?> loadClaZZ(String name) throws ClassNotFoundException {
            Class<?> clazz = null;
            try {
                byte[] bytes = Files.readAllBytes(Paths.get(name));
                clazz = defineClass(null, bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return clazz;
        }
    }
}
