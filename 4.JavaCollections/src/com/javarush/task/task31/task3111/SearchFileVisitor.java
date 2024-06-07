package com.javarush.task.task31.task3111;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path>{
    private String partOfName;
    private String partOfContent;
    private int maxSize = -1;
    private int minSize = -1;
    private List<Path> foundFiles = new ArrayList<>();

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length

        boolean containsName = true;
        if(partOfName!=null && !file.getFileName().toString().contains(partOfName))
            containsName = false;

        String contentStr = new String(content);
        boolean containsContent = true;
        if(partOfContent!=null && !contentStr.contains(partOfContent))
            containsContent = false;

        if(containsName && containsContent){
            if(minSize == -1 && maxSize == -1) foundFiles.add(file);
            if(minSize == -1 && maxSize != -1){
                if(content.length <= maxSize) foundFiles.add(file);
            }
            if(minSize != -1 && maxSize == -1) {
                if(content.length >= minSize) foundFiles.add(file);
            }
            if(minSize != -1 && maxSize != -1){
                if(content.length <= maxSize && content.length >= minSize) foundFiles.add(file);
            }
        }

        return super.visitFile(file, attrs);
    }
}
