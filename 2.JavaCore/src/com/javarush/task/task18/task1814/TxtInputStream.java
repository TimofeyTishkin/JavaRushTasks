package com.javarush.task.task18.task1814;

import java.io.*;
import java.nio.channels.FileChannel;

/* 
UnsupportedFileName
*/

public class TxtInputStream extends FileInputStream {
    private FileInputStream fi;
    public TxtInputStream( File file) throws FileNotFoundException {
        super(file);

    }

    public TxtInputStream(FileDescriptor fdObj) {
        super(fdObj);
    }

    @Override
    public int read() throws IOException {
        return fi.read();
        //super.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return  fi.read(b);
        //super.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return  fi.read(b,off,len);
        //super.read(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        return  fi.skip(n);
        //super.skip(n);
    }

    @Override
    public int available() throws IOException {
        return fi.available();
        //super.available();
    }

    @Override
    public void close() throws IOException {
        super.close();
        fi.close();
    }

    @Override
    public FileChannel getChannel() {
        return fi.getChannel();
        //super.getChannel();
    }

    @Override
    protected void finalize() throws IOException {
        super.finalize();

    }

    public TxtInputStream(String fileName) throws UnsupportedFileNameException, IOException {
        super(fileName);
        String[] fileParts=fileName.split("\\.");
        if (!"txt".equals(fileParts[fileParts.length-1])){
            super.close();
            throw  new UnsupportedFileNameException();


        }else fi=new FileInputStream(fileName);
    }

    public static void main(String[] args) {
    }
}

