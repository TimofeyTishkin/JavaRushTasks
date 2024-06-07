package com.javarush.task.task14.task1421;

 class Singleton{
    private static Singleton instance;
private Singleton(){
    if (getInstance() != null){
        return;
    }
}
    public static Singleton getInstance(){
        return instance;
    }
}
