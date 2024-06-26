package com.javarush.task.task16.task1630;

import java.io.*;

public class Solution {
    public static String firstFileName;
    public static String secondFileName;
    static {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            firstFileName = reader.readLine();
            secondFileName = reader.readLine();
            reader.close();
        } catch (IOException e) {
            firstFileName = null;
            secondFileName = null;
        }
    }
    //add your code here - добавьте код тут

    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        f.join();
        //add your code here - добавьте код тут
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }
    public static class ReadFileThread extends Thread implements ReadFileInterface{
        private String fullFileName;
        private String content = "";
        @Override
        public void run() {
            InputStream stream;
            BufferedReader reader;
            try {
                stream = new FileInputStream(fullFileName);
                reader = new BufferedReader(new InputStreamReader(stream));
                while(reader.ready() && !fullFileName.isEmpty()){
                    content += reader.readLine() + " ";
                }
                stream.close();
                reader.close();
            } catch(IOException e){

            }
        }

        @Override
        public void setFileName(String fullFileName) {
            this.fullFileName = fullFileName;
        }

        @Override
        public String getFileContent() {
            return content;
        }
    }
    //add your code here - добавьте код тут
}
