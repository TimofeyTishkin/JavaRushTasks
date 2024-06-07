package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class CurrencyManipulator {

    private String currencyCode;

    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count){
        denominations.put(denomination, denominations.containsKey(denomination)?
                denominations.get(denomination) + count : count);
    }

    public int getTotalAmount(){
        AtomicInteger result = new AtomicInteger();
        denominations.forEach((integer, integer2) -> result.addAndGet(integer*integer2));
        return result.get();
    }

    public boolean hasMoney(){
        return getTotalAmount()>0;
    }

    public boolean isAmountAvailable(int expectedAmount){
        return getTotalAmount()>=expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {

        Map<Integer, Integer> mapresult = new TreeMap<>(Collections.reverseOrder());
        int ostatok = expectedAmount;

        List<Integer> list = denominations.keySet().stream()
                .filter(key -> denominations.get(key) > 0)
                .filter(key -> key <= expectedAmount)
                .sorted((o1, o2) -> o2 - o1)
                .collect(Collectors.toList());

        int startange;

        while (ostatok!=0&&list.size()>0) {
            startange = list.get(0);
            for (int tange : list) {

                int polozheno = ostatok / tange > denominations.get(tange) ? denominations.get(tange) : ostatok / tange;
                mapresult.put(tange, polozheno);
                ostatok = ostatok - (mapresult.get(tange) * tange);
            }
            if (ostatok!=0) {
                ostatok=ostatok+startange;
                mapresult.put(startange,(mapresult.get(startange)-1));
                list.remove(0);
            }
        }

        if (ostatok!=0){throw new NotEnoughMoneyException();}
        else {
            for (Map.Entry<Integer,Integer> entry:mapresult.entrySet()) {
                if(entry.getValue()!=0) {
                    denominations.put(entry.getKey(),denominations.get(entry.getKey())-entry.getValue());
                }
            }
        }

        mapresult.entrySet().removeIf(k -> k.getValue()==0);
        
        return mapresult;
    }


}
