package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"deposit_en");
    @Override
    public void execute() throws InterruptOperationException {
        try {
            String type = ConsoleHelper.askCurrencyCode();
            String[] digits = ConsoleHelper.getValidTwoDigits(type);
            ConsoleHelper.writeMessage(res.getString("before"));
            CurrencyManipulatorFactory.getManipulatorByCurrencyCode(type)
                    .addAmount(Integer.parseInt(digits[0]), Integer.parseInt(digits[1]));
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"),
                    Integer.parseInt(digits[0]) * Integer.parseInt(digits[1]), type));
        } catch (NumberFormatException e) {
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }
    }
}
