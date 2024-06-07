package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;


public class Hippodrome {
    public Horse getWinner(){
        double maxDistance = getHorses().get(0).getDistance();
        int j = 0;
            for(int i = 0 ; i<getHorses().size(); i++){
                if(getHorses().get(i).getDistance() > maxDistance){
                    maxDistance = getHorses().get(i).getDistance();
                    j = i;
                }
            }
        return getHorses().get(j);
    }
    public void printWinner(){
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
    public void run() throws InterruptedException{
        for(int i = 0; i < 100; i++){
            move();
            print();
            Thread.sleep(200);
        }
    }
    public void move(){
        for(Horse horse :horses){
            horse.move();
        }
    }
    public void print(){
        for(Horse horse :horses){
            horse.print();
        }
        System.out.println(); System.out.println();System.out.println();
        System.out.println();System.out.println();System.out.println();
        System.out.println();System.out.println();System.out.println();
        System.out.println();
    }

    public static Hippodrome game;
    public boolean isGameEnded;
    private List<Horse> horses;

    public Hippodrome(){}
    public Hippodrome(List<Horse> list){
        horses = list;
    }

    public List<Horse> getHorses() {
        return horses;
    }
    public static void main(String[] args) {
        Horse horse1 = new Horse("Speedy", 3, 0);
        Horse horse2 = new Horse("Lucky", 3, 0);
        Horse horse3 = new Horse("Poky", 3, 0);
        List<Horse> horses = new ArrayList<>();
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);
        Hippodrome.game = new Hippodrome(horses);
        try{
            game.run();
        } catch (InterruptedException e){
            System.out.println("ДЭбил");
        }
        game.printWinner();
    }
}
