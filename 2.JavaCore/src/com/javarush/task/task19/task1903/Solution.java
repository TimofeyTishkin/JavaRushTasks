package com.javarush.task.task19.task1903;

/* 
Адаптация нескольких интерфейсов
*/

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static Map<String, String> countries = new HashMap<>();
    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");

    }

    public static void main(String[] args) {

    }

    public static class IncomeDataAdapter implements Customer, Contact {
        private IncomeData data;
        public IncomeDataAdapter(IncomeData incomeData){
            this.data = incomeData;
        }
        @Override
        public String getCompanyName() {
            return data.getCompany();
        }
        @Override
        public String getCountryName() {
            return countries.get(data.getCountryCode());
        }
        @Override
        public String getName() {
            return data.getContactLastName() +", " + data.getContactFirstName();
        }
        @Override
        public String getPhoneNumber() {
            String  countryCode = '+'+String.valueOf(data.getCountryPhoneCode()),
                    phoneNumber = String.valueOf(data.getPhoneNumber());
            StringBuffer stringBufferPhoneNumber = new StringBuffer(phoneNumber.subSequence(0,phoneNumber.length()));
            while (stringBufferPhoneNumber.length()<10){
                stringBufferPhoneNumber.insert(0,'0');
            }
            stringBufferPhoneNumber.insert(0,'(');
            stringBufferPhoneNumber.insert(4,')');
            stringBufferPhoneNumber.insert(8,'-');
            stringBufferPhoneNumber.insert(11,'-');
            phoneNumber = stringBufferPhoneNumber.toString();

            return countryCode + phoneNumber;
        }
    }


    public static interface IncomeData {
        String getCountryCode();        //For example: UA

        String getCompany();            //For example: JavaRush Ltd.

        String getContactFirstName();   //For example: Ivan

        String getContactLastName();    //For example: Ivanov

        int getCountryPhoneCode();      //For example: 38

        int getPhoneNumber();           //For example: 501234567
    }

    public static interface Customer {
        String getCompanyName();        //For example: JavaRush Ltd.

        String getCountryName();        //For example: Ukraine
    }

    public static interface Contact {
        String getName();               //For example: Ivanov, Ivan

        String getPhoneNumber();        //For example: +38(050)123-45-67
    }
}