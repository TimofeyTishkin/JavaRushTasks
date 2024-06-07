package com.javarush.task.task24.task2401;

public class SelfInterfaceMarkerImpl implements SelfInterfaceMarker {
    public void doNothingForMinute() throws InterruptedException{
        for(int i = 0; i < 60;i++){
            Thread.sleep(1000);
        }
    }
    public void printSmth(){
        System.out.println("Smth");
    }
}
