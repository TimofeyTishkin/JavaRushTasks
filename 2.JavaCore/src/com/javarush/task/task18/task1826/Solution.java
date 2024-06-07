package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        switch (args[0]){
            case ("-e") : {
                FileInputStream File = new FileInputStream(args[1]);
                FileOutputStream FileOut = new FileOutputStream(args[2]);
                int Readddy;
                while((Readddy = File.read()) != -1){
                    if((char)Readddy == '.'){
                        Readddy = '~';
                    }
                    FileOut.write(Readddy);
                }
                File.close();
                FileOut.close();
                break;
            }
            case ("-d") : {
                FileInputStream File = new FileInputStream(args[1]);
                FileOutputStream FileOut = new FileOutputStream(args[2]);
                int Readddy;
                while((Readddy = File.read()) != -1){
                    if((char)Readddy == '~'){
                        Readddy = '.';
                    }
                    FileOut.write(Readddy);
                }
                File.close();
                FileOut.close();
                break;
            }
        }
    }

}
