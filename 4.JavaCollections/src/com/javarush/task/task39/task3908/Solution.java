package com.javarush.task.task39.task3908;

import java.util.ArrayList;

/*
Возможен ли палиндром?
*/
public class Solution {
    public static void main(String[] args) {

    }

    public static boolean isPalindromePermutation(String s) {
        s = s.toLowerCase();
        ArrayList<Character> arrayList=new ArrayList<>();
        boolean x=false;
        for(int i=0;i<s.length();i++){
            if(i==0){
                arrayList.add(s.charAt(i));
            }else{
                for (Character character : arrayList) {
                    if (s.charAt(i) == character) {
                        x = true;
                        break;
                    }
                }
                if(!x){
                    arrayList.add(s.charAt(i));
                }
                x=false;
            }
        }

        ArrayList<Boolean> arrayBoolean=new ArrayList<>();
        int count=0;
        for(int j=0;j<arrayList.size();j++){
            System.out.println(arrayList.get(j));
            for(int i=0;i<s.length();i++){
                if(arrayList.get(j)==s.charAt(i)){
                    count++;
                }
            }
            if(count%2==0){
                arrayBoolean.add(true);
            }else{
                arrayBoolean.add(false);
            }
            count=0;
            System.out.println(arrayBoolean.get(j));
        }

        boolean result=false;
        if(s.length()%2==0){
            result=true;
            for(int i=0;i<arrayBoolean.size();i++){
                if(!arrayBoolean.get(i)){
                    result=false;
                }
            }
        }else{
            for(int i=0;i<arrayBoolean.size();i++){
                if(result&&!arrayBoolean.get(i)){
                    result=false;
                    break;
                }
                if(!arrayBoolean.get(i)){
                    result=true;
                }
            }
        }

        System.out.println("Result "+result);
        return result;
    }
}
