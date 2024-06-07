package com.javarush.task.task15.task1504;

import java.util.LinkedList;
import java.util.List;

/* 
ООП - книги
*/

public class Solution {
    public static class AgathaChristieBook extends Book{
        private final static String author = "Agatha Christie";
        public String title;
        @Override
        public AgathaChristieBook getBook(){
            return this;
        }
        public AgathaChristieBook(String title) {
            super(author);
            this.title = title;
        }
        @Override
        public String getTitle() {
            return title;
        }
    }
    public static class MarkTwainBook extends Book{
        private final static String author = "Mark Twain";
        public String title;
        @Override
        public MarkTwainBook getBook(){
            return this;
        }
        public MarkTwainBook(String title) {
            super(author);
            this.title = title;
        }
        @Override
        public String getTitle() {
            return title;
        }
    }
    public static void main(String[] args) {
        List<Book> books = new LinkedList<>();
        books.add(new MarkTwainBook("Tom Sawyer"));
        books.add(new AgathaChristieBook("Hercule Poirot"));
        System.out.println(books);
    }

    abstract static class Book {
        private String author;

        public Book(String author) {
            this.author = author;
        }

        public abstract Book getBook();

        public abstract String getTitle();

        private String getOutputByBookType() {
            String agathaChristieOutput = author + ": " + getBook().getTitle() + " is a detective";
            String markTwainOutput = getBook().getTitle() + " was written by " + author;

            String output = "output";
            if(author.equals("Mark Twain")) output = markTwainOutput;
            if(author.equals("Agatha Christie")) output = agathaChristieOutput;
            //Add your code here

            return output;
        }

        public String toString() {
            return getOutputByBookType();
        }
    }
}
