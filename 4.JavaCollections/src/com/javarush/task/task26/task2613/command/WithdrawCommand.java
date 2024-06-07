package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.ResourceBundle;

class WithdrawCommand implements Command{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String type = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator mani = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(type);
        boolean allRight = false;
        while (!allRight){
            int val;
            try {
                ConsoleHelper.writeMessage(res.getString("specify.amount"));
                val = Integer.parseInt(ConsoleHelper.readString());
                allRight = true;
            } catch (NumberFormatException e) {
                allRight = false;
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            }
            try {
                if(mani.isAmountAvailable(val)){
                    mani.withdrawAmount(val).forEach((integer, integer2) ->
                            ConsoleHelper.writeMessage("\t"+integer +" - "+integer2));
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"),
                            val, mani.getCurrencyCode()));
                } else {
                    allRight = false;
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                }
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                allRight = false;
            }
        }
    }
}
