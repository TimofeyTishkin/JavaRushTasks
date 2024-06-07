package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader readerOfName = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader readerFile1 = new BufferedReader(new FileReader(readerOfName.readLine()));
        BufferedReader readerFile2 = new BufferedReader(new FileReader(readerOfName.readLine()));
        readerOfName.close();
        ArrayList<String> dataFile1 = new ArrayList<>();
        ArrayList<String> dataFile2 = new ArrayList<>();
        while (readerFile1.ready() || readerFile2.ready()){
            if(readerFile1.ready()) dataFile1.add(readerFile1.readLine());
            if(readerFile2.ready()) dataFile2.add(readerFile2.readLine());
        } readerFile1.close();
        readerFile2.close();

        for(int i = 0; i < dataFile1.size() || i < dataFile2.size(); i++){
            String strOf1;
            String strOf2;
            try{
                strOf1 = dataFile1.get(i);
            }catch (IndexOutOfBoundsException e){
                try{lines.add(new LineItem(Type.ADDED, dataFile2.get(i)));}
                catch (IndexOutOfBoundsException nen){
                    break;
                }
                break;
            }
            try{
                strOf2 = dataFile2.get(i);
            }catch (IndexOutOfBoundsException e){
                try{lines.add(new LineItem(Type.REMOVED, dataFile1.get(i)));}
                catch (IndexOutOfBoundsException nen){
                    break;
                }
                break;
            }
            if(strOf1.equals(strOf2)){
                lines.add(new LineItem(Type.SAME, strOf2));
            } else {
                try{
                    String strOf3 = dataFile1.get(i+1);
                    if(strOf3.equals(dataFile2.get(i))){
                        lines.add(new LineItem(Type.REMOVED, dataFile1.get(i)));
                        dataFile1.remove(i);
                    } else {
                        lines.add(new LineItem(Type.ADDED, dataFile2.get(i)));
                        dataFile2.remove(i);
                    }
                    i--;
                } catch (IndexOutOfBoundsException e){
                    break;
                }
            }
        }

    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
