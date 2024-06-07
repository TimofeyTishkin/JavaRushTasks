package com.javarush.task.task29.task2909.car;

import java.util.Date;

public abstract class Car {
    static public final int TRUCK = 0;
    static public final int SEDAN = 1;
    static public final int CABRIOLET = 2;

    double fuel;

    public double summerFuelConsumption;
    public double winterFuelConsumption;
    public double winterWarmingUp;

    private final int type;

    private boolean driverAvailable;
    private final int numberOfPassengers;

    protected Car(int type, int numberOfPassengers) {
        this.type = type;
        this.numberOfPassengers = numberOfPassengers;
    }

    public void fill(double numberOfLiters) throws Exception {
        if (numberOfLiters < 0)
            throw new Exception();
        fuel += numberOfLiters;
    }

    public double getTripConsumption(Date date, int length, Date SummerStart, Date SummerEnd) {
        double consumption;
        if (!isSummer(date, SummerStart, SummerEnd)) {
            consumption = getWinterConsumption(length);
        } else {
            consumption = getSummerConsumption(length);
        }
        return consumption;
    }

    public boolean isDriverAvailable() {
        return driverAvailable;
    }

    public void setDriverAvailable(boolean driverAvailable) {
        this.driverAvailable = driverAvailable;
    }

    public void startMoving() {
        if (numberOfPassengers > 0) {
            fastenPassengersBelts();
        }
        fastenDriverBelt();
    }

    public void fastenPassengersBelts() {
    }

    public void fastenDriverBelt() {
    }
    public final int MAX_TRUCK_SPEED = 80;
    public final int MAX_SEDAN_SPEED = 120;
    public final int MAX_CABRIOLET_SPEED = 90;
    public abstract int getMaxSpeed();
    public static Car create(int type, int numberOfPassengers){
        if(type == 0){
            return new Truck(numberOfPassengers);
        } else if(type == 1){
            return new Sedan(numberOfPassengers);
        } else if(type == 2){
            return new Cabriolet(numberOfPassengers);
        }
        return null;
    }
    public boolean isSummer(Date date, Date summerStart, Date summerEnd){
        return date.before(summerEnd) && date.after(summerStart);
    }
    public double getWinterConsumption(int length){
        return length * winterFuelConsumption + winterWarmingUp;
    }
    public double getSummerConsumption(int length){
        return length * summerFuelConsumption;
    }
    private boolean canPassengersBeTransferred(){
        return isDriverAvailable() && fuel>0;
    }
    public int getNumberOfPassengersCanBeTransferred(){
        if(canPassengersBeTransferred()){
            return this.numberOfPassengers;
        }
        return 0;
    }
}