package com.javarush.task.task22.task2209;

import java.io.*;
import java.util.*;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileReader fileReader = new FileReader(reader.readLine())){
            while(fileReader.ready()){
                stringBuilder.append((char)fileReader.read());
            }
        } catch (IOException e){
            System.out.println("Why?");
        }
        //...
        StringBuilder result = getLine(stringBuilder.toString().replaceAll("[\\n\\r]", " ").split("\\s"));
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        if (words.length == 0) return new StringBuilder("");

        Map<String, char[]> map = new HashMap<>();
        ArrayList<String> currentWords = new ArrayList<>();
        for (int i = 0; i < words.length; i++){
            currentWords.add(words[i]);
        }

        char[] symbolsOfWord;
        char[] symbols;
        for (int i = 0; i < words.length; i++){
            symbolsOfWord = words[i].toLowerCase().toCharArray();
            symbols = new char[]{symbolsOfWord[0], symbolsOfWord[symbolsOfWord.length - 1]};
            map.put(words[i], symbols);
        }

        ArrayList<String> reserve = new ArrayList<>();
        for (String word : currentWords){
            reserve.add(word);
        }

        StringBuilder str = new StringBuilder("");
        char currentSymbol;
        char lastSymbol;
        int cycleCount = 0;
        int count = 0;

        for (int i = 0; currentWords.size() != 0; i++) {
            currentWords = new ArrayList<>();
            for (String word : reserve){
                currentWords.add(word);
            }

            str = new StringBuilder("");
            str.append(currentWords.get(i));
            lastSymbol = map.get(currentWords.get(i))[1];
            currentWords.remove(i);

            while (currentWords.size() > 0) {
                currentSymbol = map.get(currentWords.get(count))[0];
                if (currentSymbol == lastSymbol) {
                    lastSymbol = map.get(currentWords.get(count))[1];
                    str.append(" " + currentWords.get(count));
                    currentWords.remove(count);
                    cycleCount = 0;
                } else count++;

                if (count == currentWords.size() && cycleCount == 0){
                    count = 0;
                    cycleCount++;
                } else {
                    if (count == currentWords.size() && cycleCount > 0) {
                        cycleCount = 0;
                        count = 0;
                        break;
                    }
                }
            }
        }

        return str;
    }
}
