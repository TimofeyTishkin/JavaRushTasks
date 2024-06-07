package com.javarush.task.task18.task1825;

/* 
Собираем файл
*/


import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception{
        ArrayList<Stream> arrS = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while(true){
                String s = reader.readLine();
                if("end".equals(s)) break;
                arrS.add(new Stream(s));
            }
            int part = 0;
            FileOutputStream fileOut = new FileOutputStream(
                    new File(arrS.get(0).name.substring(0,arrS.get(0).name.lastIndexOf(".part"))));
            for(int x = 0;part<arrS.size();x++) {

                for (Stream s : arrS) {
                    String str = s.name;
                    if (x == Integer.parseInt(str.substring(str.lastIndexOf(".part") + 5))) {
                        byte[] arr = new byte[s.getStream().available()];
                        s.getStream().read(arr);
                        fileOut.write(arr);
                        part++;

                    }
                }

            }
            for(Stream s : arrS){
                s.getStream().close();
            }
            fileOut.close();
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static class Stream{
        protected String name;
        protected FileInputStream Stream;

        Stream(String name){
            this.name = name;
        }

        public String getName(){
            return this.name;
        }

        public FileInputStream getStream()throws Exception{
            Stream =  new FileInputStream(name);
            return Stream;
        }
    }
}
