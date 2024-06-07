package com.javarush.task.task19.task1921;

import jdk.nashorn.internal.ir.WhileNode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        SimpleDateFormat simple = new SimpleDateFormat("dd MM yyyy");
        while(reader.ready()){
            String personLine = reader.readLine();
            String[] personInfo = personLine.split("\\s");
            int pil = personInfo.length - 1;
            String personDate = personInfo[pil-2]+" "+personInfo[pil-1]+" "+personInfo[pil];
            Date birthDate = simple.parse(personDate);
            StringBuilder personName = new StringBuilder();
            for(String s : personInfo){
                if(s.equals(personInfo[pil - 2])){
                    break;
                }
                personName.append(s).append(" ");
            }
            PEOPLE.add(new Person(personName.toString().trim(), birthDate));
        }reader.close();
    }
}
