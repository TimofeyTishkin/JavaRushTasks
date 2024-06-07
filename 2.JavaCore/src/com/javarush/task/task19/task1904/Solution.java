package com.javarush.task.task19.task1904;

/* 
И еще один адаптер
*/

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {

    }

    public static class PersonScannerAdapter implements PersonScanner {
        private Scanner fileScanner;
        public PersonScannerAdapter(Scanner scanner) {
            this.fileScanner = scanner;
        }
        @Override
        public Person read() throws ParseException{
            String[] arr = fileScanner.nextLine().split(" ");
            String lastName = arr[0];
            String firstName = arr[1];
            String middleName = arr[2];
            String stringData = arr[3]+" "+arr[4]+" "+arr[5];
            SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy", Locale.getDefault());
            Date dateOfBirth = sdf.parse(stringData);

            return new Person(firstName,middleName,lastName,dateOfBirth);
        }
        @Override
        public void close() {
            fileScanner.close();
        }
    }
}
