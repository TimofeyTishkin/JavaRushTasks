package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class LoginCommand implements Command{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"login_en");
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"verifiedCards");

    @Override
    public void execute() throws InterruptOperationException {
        String cardNumber;
        String pin;
        while (true) {
            ConsoleHelper.writeMessage(res.getString("before"));
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            cardNumber = ConsoleHelper.readString();
            pin = ConsoleHelper.readString();
            Pattern numberPattern = Pattern.compile("\\d{12}");
            Pattern pinPattern = Pattern.compile("\\d{4}");
            if(numberPattern.matcher(cardNumber).matches() && pinPattern.matcher(pin).matches()) {
                if (!(validCreditCards.containsKey(cardNumber) &&
                        validCreditCards.getString(cardNumber).equals(pin))) {
                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), cardNumber));
                    continue;
                } else break;
            } else {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                continue;
            }

        }
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), cardNumber));

    }

}
