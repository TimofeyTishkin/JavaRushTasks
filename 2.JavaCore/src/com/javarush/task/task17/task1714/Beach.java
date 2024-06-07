package com.javarush.task.task17.task1714;

/* 
Comparable
*/

public class Beach implements Comparable<Beach> {
    private volatile String name;      //название
    private volatile float distance;   //расстояние
    private volatile int quality;    //качество

    public Beach(String name, float distance, int quality) {
        this.name = name;
        this.distance = distance;
        this.quality = quality;
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public synchronized float getDistance() {
        return distance;
    }

    public synchronized void setDistance(float distance) {
        this.distance = distance;
    }

    public synchronized int getQuality() {
        return quality;
    }

    public synchronized void setQuality(int quality) {
        this.quality = quality;
    }

    public static synchronized void main(String[] args) {
        System.out.println(new Beach("bb", 100, 5).compareTo(new Beach("sss", 120, 4)));
    }

    @Override
    public synchronized int compareTo(Beach o) {
        int res1 = 0;
        int res2 = 0;
        if(this.distance > o.distance){
            res1--;
        } else if(this.distance < o.distance){
            res2--;
        }
        if(this.quality > o.quality){
            res1++;
        } else if(this.quality < o.quality){
            res2++;
        }

        return res1 - res2;
    }
}
