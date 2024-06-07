package com.javarush.task.task17.task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat mDf = new SimpleDateFormat("dd/MM/yyyy");
         if(args[0].equals("-c")){
             if(args[2].equals(Sex.MALE.toString())) {
                 allPeople.add(Person.createMale(args[1], mDf.parse(args[3])));
                 System.out.println(allPeople.size() - 1);
             }
             if(args[2].equals(Sex.FEMALE.toString())){
                 allPeople.add(Person.createFemale(args[1], mDf.parse(args[3])));
                 System.out.println(allPeople.size() - 1);
             }
         }
         if(args[0].equals("-u")){
             if(args[3].equals(Sex.MALE.toString())) {
                 allPeople.set(Integer.parseInt(args[1]), Person.createMale(args[2], mDf.parse(args[4])));
             }
             if(args[3].equals(Sex.FEMALE.toString())){
                 allPeople.set(Integer.parseInt(args[1]), Person.createFemale(args[2], mDf.parse(args[4])));
             }
         }
         if(args[0].equals("-d")){
             allPeople.get(Integer.parseInt(args[1])).setName(null);
             allPeople.get(Integer.parseInt(args[1])).setSex(null);
             allPeople.get(Integer.parseInt(args[1])).setBirthDate(null);
         }
         if(args[0].equals("-i")){
             System.out.println(allPeople.get(Integer.parseInt(args[1])));
         }
        //start here - начни тут
    }
}
