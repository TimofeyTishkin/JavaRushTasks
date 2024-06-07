package com.javarush.task.task19.task1927;

/* 
Контекстная реклама
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);
        testString.printSomething();
        String[] halfResult = outputStream.toString().split("\\n");
        StringBuilder resultBuilder = new StringBuilder();
        for(int i = 0; i < halfResult.length; i++){
            resultBuilder.append(halfResult[i]).append("\n");
            if(i%2!=0){
                resultBuilder.append("JavaRush - курсы Java онлайн").append("\n");
            }
        }
        String result = resultBuilder.toString();
        System.setOut(consoleStream);
        System.out.println(result);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
