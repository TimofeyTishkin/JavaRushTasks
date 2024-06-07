package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"common_en");

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        try {
            String answ = bis.readLine();
            if(answ.equalsIgnoreCase("EXIT")){
                throw new InterruptOperationException();
            }
            return answ;
        } catch (IOException ignore) {
        }
        return "";
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage(res.getString("choose.currency.code"));
        String currencyCode = readString();
        if(currencyCode.length() == 3) return currencyCode.toUpperCase();
        else {
            writeMessage(res.getString("invalid.data"));
            return askCurrencyCode();
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        try {
            writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
            String gotten = readString();
            if(gotten.split("\\s").length != 2) throw new IllegalArgumentException();
            int val = Integer.parseInt(gotten.split("\\s")[0].trim());
            int count = Integer.parseInt(gotten.split("\\s")[1].trim());
            if(val <= 0 || count <= 0) throw new IllegalArgumentException();
            return new String[]{String.valueOf(val), String.valueOf(count)};
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            writeMessage(res.getString("invalid.data"));
            return getValidTwoDigits(currencyCode);
        }
    }

    public static Operation askOperation() throws InterruptOperationException{
        writeMessage(res.getString("choose.operation")+
                "\n 1 - " + res.getString("operation.INFO")+"," +
                "\n 2 - " + res.getString("operation.DEPOSIT")+"," +
                "\n 3 - " + res.getString("operation.WITHDRAW")+"," +
                "\n 4 - " + res.getString("operation.EXIT")+".");
        try{
            return Operation.getAllowableOperationByOrdinal(Integer.parseInt(readString()));
        } catch (IllegalArgumentException e){
            writeMessage(res.getString("invalid.data"));
            return askOperation();
        }
    }

    public static void printExitMessage() {
        writeMessage(res.getString("the.end"));
    }

}
