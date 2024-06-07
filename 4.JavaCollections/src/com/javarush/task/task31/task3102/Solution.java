package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        return Files.walk(Paths.get(root)).filter(Files::isRegularFile).
                map(Path::toFile).map(File::getAbsolutePath).collect(Collectors.toList());
    }

    public static void main(String[] args) {

    }
}
