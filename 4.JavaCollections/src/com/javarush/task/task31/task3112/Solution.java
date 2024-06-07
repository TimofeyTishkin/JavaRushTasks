package com.javarush.task.task31.task3112;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("C:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        URL url = new URL(urlString);
        Path temptFile = Files.createTempFile(null, null);
        String fileName = urlString.substring(urlString.lastIndexOf('/'));

        Files.copy(url.openStream(),
                temptFile, StandardCopyOption.REPLACE_EXISTING);
        Path myFile = Paths.get(downloadDirectory.toString() + fileName);
        boolean wtf = myFile.toFile().mkdirs();
        Files.move(temptFile, myFile, StandardCopyOption.REPLACE_EXISTING);
        // implement this method
        return myFile;
    }
}
