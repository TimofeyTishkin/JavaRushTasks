package com.javarush.task.task20.task2027;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/*
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        List<Word> words = (detectAllWords(crossword, "home", "same"));
        for (Word word:words) {
            System.out.println(word);
        }
        System.out.println(words.size());
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> result = new ArrayList<>();
        for (int i = crossword.length-1; i >= 0; i--) {
            for (int j = 0; j < crossword[0].length; j++) {
                List<Word> detected = detect(i, j, crossword, words);
                if(!detected.isEmpty()) result.addAll(detected);
            }
        }
        List<Word> definitelyResult = new ArrayList<>();
        for (Word w:result) {
            if (w!=null) definitelyResult.add(w);
        }
        return definitelyResult;
    }
    public static List<Word> detect(int i, int j, int[][] crossword, String... words){
        List<Word> result = new ArrayList<>();
        for (String s:words) {
//            System.out.printf("testing word %s for i=%d and j=%d\n", s, i, j);
            if (s.charAt(0) == crossword[i][j]) {
//                System.out.printf("Word %s may start with %s at pisition (%d,%d)", s, (char)crossword[i][j], i, j);
                Word word = null;
                word = findWord(i, j, s, crossword);
                if (word!=null) result.add(word);
            }
        }
        return result;
    }
    public static Word findWord(int i, int j, String s, int[][] crossword){
        Word word = new Word(s);
        word.setStartPoint(j, i);

        Pair<Integer, Integer> endPoint = null;
        for (int k = 1; k < 10; k++) {
            if (k==5) continue;
//            System.out.println("Checking K = " + k);
            endPoint = findEndPoint(k, i, j, s, crossword);
            if (endPoint != null) {
//                System.out.println("endPosition returned!");
                break;
            }
        }

        if (endPoint != null) {
            word.setEndPoint(endPoint.getKey(), endPoint.getValue());
            return word;
        }
        return null;
    }
    public static Pair<Integer, Integer> findEndPoint(int direction, int i, int j, String word, int[][] crossword){
        if (word.charAt(0)!=crossword[i][j]) return null;
        int dx, dy;
        switch(direction){
            case(1):{dx =-1;dy =-1;break;}
            case(2):{dx = 0;dy =-1;break;}
            case(3):{dx = 1;dy =-1;break;}
            case(4):{dx =-1;dy = 0;break;}
            case(6):{dx = 1;dy = 0;break;}
            case(7):{dx =-1;dy = 1;break;}
            case(8):{dx = 0;dy = 1;break;}
            case(9):{dx = 1;dy = 1;break;}
            default:{dx = 0;dy = 0;}
        }
        if ( (dx*word.length() + j+1 < 0) || (j + dx*word.length() > crossword[i].length) ) return null;
        if ( (dy*word.length() + i+1 < 0) || (i + dy*word.length() > crossword.length) ) return null;
        if (word.length() == 1) {
//            System.out.println("Definitely returning pair");
            return new Pair<>(j, i);
        }
        {
//            System.out.printf("Recoursing word \"%s\"(%d,%d) to \"%s\"(%d,%d)\n", word, j, i, word.substring(1), j+dx, i+dy);
            return findEndPoint(direction, i + dy, j + dx, word.substring(1), crossword);
        }
    }


    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}

