package com.javarush.task.task31.task3101;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) {
        Path path0 = Paths.get(args[0]);
        File resultFile = new File(args[1]);
        FileUtils.renameFile(resultFile, new File(resultFile.getParent()+"/allFilesContent.txt"));

        List<Path> allFilesUnder50AndReadyToRead = recursiveSortingOfDirectories(path0);
        try(FileWriter fileOut = new FileWriter(resultFile.getParent() + "/allFilesContent.txt")){
            for(Path path : allFilesUnder50AndReadyToRead){
                try(FileReader fileIn = new FileReader(path.toFile())){
                    while (fileIn.ready()){
                        fileOut.write((char)fileIn.read());
                    }
                    fileOut.write('\n');
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<Path> recursiveSortingOfDirectories(Path path){
        List<Path> filesUnder50 = new ArrayList<>();
        //if(path != null && path.toFile().isFile()) return Collections.singletonList(path);

        File[] files = path.toFile().listFiles();
        for(int i = 0; i < Objects.requireNonNull(files).length; i++) {
            // Если file является директорией, то рекурсивно вызываем
            // метод recursiveSortingOfDirectories
            if (files[i].isDirectory()) {
                filesUnder50.addAll(recursiveSortingOfDirectories(files[i].toPath()));
            } else {
                if(files[i].length() <= 50) {
                    filesUnder50.add(files[i].toPath());
                }
            }
        }
        filesUnder50.sort(Comparator.comparing(o -> o.toFile().getParent().concat("/").concat(o.toFile().getName())));
       return filesUnder50;
    }
}
