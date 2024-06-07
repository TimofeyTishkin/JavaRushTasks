package com.javarush.task.task15.task1527;

/* 
Парсер реквестов
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in)); //считываем с клавы строку
        URL myURL = new URL(bufferedReader.readLine()); // делаем из нее URL
        String[] Pairs = myURL.getQuery().split("&"); //загоняем в массив все, что после "?" и между "&"
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>(); //упорядоченная коллекция параметров и значений
        for (int i = 0; i < Pairs.length; i++) { //идем по всему массиву
            String[] parametersTemp = Pairs[i].toString().split("="); // и нарезаем во временный масиив все, где есть "="
            if (parametersTemp.length > 1) // если знак "=" был и есть кроме параметра еще и его значние
                hashMap.put(parametersTemp[0], parametersTemp[1]); //записваем пару параметр и значение в коллекцию
            else
                hashMap.put(parametersTemp[0], null); // иначе пишем только параметр, значение делаем пустым
        }
        int i = 0; // переменная для удаления последнего пробела (думал, что поможет при валидации)
        for (Map.Entry<String, String> me : hashMap.entrySet()) {
            System.out.print(me.getKey()); // выводим имя параметра
            i++;
            if (i != hashMap.size()) System.out.print(" "); // и если он не последний, то херачим еще и пробел
        }
        for (Map.Entry<String, String> me : hashMap.entrySet()) { //пробегаясь снова по коллекции
            if (me.getKey().equals("obj")) { //ищем, есть ли параметр с именем "obj", если есть, то
                System.out.println();
                try {
                    alert(Double.parseDouble(me.getValue())); //пробуем конвертнуть в дабл и вызвать соответствующий алерт
                } catch (NumberFormatException e) { //если не получилось
                    alert(me.getValue()); // вызываем алерт со строковым полем
                }
            }
        }
    }
    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }
}
