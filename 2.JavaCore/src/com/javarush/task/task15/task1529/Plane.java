package com.javarush.task.task15.task1529;

public class Plane implements CanFly {
    protected int Passengers;
    @Override
    public void fly() {
        System.out.println("I can fly");
    }
    public Plane(int Passengers){
        this.Passengers = Passengers;
    }
}
