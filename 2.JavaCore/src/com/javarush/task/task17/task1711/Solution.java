package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        switch (args[0]){
            case ("-c") : {
                synchronized (allPeople){
                    try{
                        for(int i = 1; i < (args.length - 2); i++){
                            if(args[i + 1].equals(Sex.MALE.toString())){
                                allPeople.add(Person.createMale(args[i], sdf.parse(args[i+2])));
                                System.out.println(allPeople.size() - 1);

                            }
                            if(args[i + 1].equals(Sex.FEMALE.toString())){
                                allPeople.add(Person.createFemale(args[i], sdf.parse(args[i+2])));
                                System.out.println(allPeople.size() - 1);
                            }
                        }
                    } catch (ParseException e) {
                        System.out.println("ошибо4ка");
                    }
                }
                break;
            } case ("-u") : {
                synchronized (allPeople){
                    try{
                        for(int i = (args.length - 1);i > 3; i--){
                            if(args[i - 1].equals(Sex.MALE.toString())){
                                allPeople.set(Integer.parseInt(args[i-3]), Person.createMale(args[i-2], sdf.parse(args[i])));
                            }
                            if(args[i - 1].equals(Sex.FEMALE.toString())){
                                allPeople.set(Integer.parseInt(args[i-3]), Person.createFemale(args[i-2], sdf.parse(args[i])));
                            }
                        }
                    } catch (ParseException e){
                        System.out.println("ошибо4ка");
                    }
                }
                break;
            } case ("-d") : {
                synchronized (allPeople){
                    try{
                        for(int i = (args.length - 1);i > 0; i--){
                            allPeople.get(Integer.parseInt(args[i])).setBirthDate(null);
                            allPeople.get(Integer.parseInt(args[i])).setName(null);
                            allPeople.get(Integer.parseInt(args[i])).setSex(null);
                        }
                    } catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("ошибо4ка");
                    }
                }
                break;
            } case ("-i") : {
                synchronized (allPeople){
                    try{
                        for(int i = 1; i < args.length; i++){
                            System.out.println(allPeople.get(Integer.parseInt(args[i])));
                        }
                    } catch (Exception e){
                        System.out.println("ошибо4ка");
                    }
                }
                break;
            }
        }
            //start here - начни тут
    }

}
