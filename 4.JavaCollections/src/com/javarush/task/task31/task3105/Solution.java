package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Map<String, ByteArrayOutputStream> zipData = new LinkedHashMap<>();

        Path fileName = Paths.get(args[0]);
        String zipPath = args[1];

        ZipInputStream archiveIn = new ZipInputStream(new FileInputStream(zipPath));
        ByteArrayOutputStream outputStream;
        ZipEntry entry;
        while((entry = archiveIn.getNextEntry()) != null){
            outputStream = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int end;
            while((end = archiveIn.read(buf)) > 0){
                outputStream.write(buf, 0, end);
            }

            zipData.put(entry.getName(), outputStream);
            archiveIn.closeEntry();
        }
        archiveIn.close();

        ZipOutputStream archiveOut = new ZipOutputStream(new FileOutputStream(zipPath));
        archiveOut.putNextEntry(new ZipEntry("/new/"+fileName.toFile().getName()));
        Files.copy(fileName, archiveOut);
        archiveOut.closeEntry();
        for(Map.Entry<String, ByteArrayOutputStream> pair : zipData.entrySet()){
            archiveOut.putNextEntry(new ZipEntry(pair.getKey()));
            archiveOut.write(pair.getValue().toByteArray());
            archiveOut.closeEntry();
        }
        archiveOut.close();
    }
}
