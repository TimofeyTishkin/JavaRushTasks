package com.javarush.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        if(f.isDirectory()) return true;
        String s = f.getName().substring(f.getName().length() - 4);
        String s1 = f.getName().substring(f.getName().length() - 5);
        return ".htm".equalsIgnoreCase(s) || ".html".equalsIgnoreCase(s1);
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
