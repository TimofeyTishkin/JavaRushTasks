package com.javarush.task.task26.task2613;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> map = new HashMap<>();

    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode){
        if(map.containsKey(currencyCode.toUpperCase())){
            return map.get(currencyCode.toUpperCase());
        } else {
            CurrencyManipulator money = new CurrencyManipulator(currencyCode);
            map.put(currencyCode.toUpperCase(), money);
            return money;
        }
    }
    public static Collection<CurrencyManipulator> getAllCurrencyManipulators(){
        return map.values();
    }
}
