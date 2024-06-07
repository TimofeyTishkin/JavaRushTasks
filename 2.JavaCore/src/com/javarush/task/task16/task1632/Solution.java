package com.javarush.task.task16.task1632;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);
static {
    threads.add(new InfinityThread());
    threads.add(new InterruptedThread());
    threads.add(new HappinessThread());
    threads.add(new MessageThread());
    threads.add(new NThread());
}
    public static void main(String[] args) {

    }
    public static class InfinityThread extends Thread{
        @Override
        public void run() {
            while(true){}
        }
    }
    public static class InterruptedThread extends Thread{
        @Override
        public void run() {
            while(true){
                try{
                    Thread.sleep(100);
                } catch (InterruptedException e){
                    System.out.println("InterruptedException");
                }
            }
        }
    }
    public static class HappinessThread extends Thread{
        @Override
        public void run() {
            while(true) {
                try {
                    System.out.println("Ура");
                    Thread.sleep(500);
                } catch (InterruptedException r) {

                }
            }
        }
    }
    public static class MessageThread extends Thread implements Message{
    private boolean Exit = true;
        @Override
        public void showWarning() {
            Exit = false;
        }

        @Override
        public void run() {
            while(Exit){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    return;
                }
            }
        }
    }
    public static class NThread extends Thread {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        @Override
        public void run() {
            int res = 0;
            try{
            while(reader.ready()){
                String d = reader.readLine();
                if(d.equals("N")){
                    System.out.println(res);
                    throw new IOException();
                }
                res += Integer.parseInt(d);
            }
        } catch (IOException e){

        }

        }
    }
}