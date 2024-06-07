package com.javarush.task.task26.task2601;

import java.util.Arrays;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
        sort(new Integer[]{13, 8, 15, 5, 17});
    }
    public static Integer[] sort(Integer[] array) {
        int median;
        Integer[] copyArray = array.clone();
            Arrays.sort(copyArray);  //сортируем полученный массив по возрастанию
            if (copyArray.length % 2 == 0) { //если количество элементов в массиве чётное
                //то возвращаем половину от суммы двух средних элементов массива
                median =  ((copyArray[copyArray.length / 2] + copyArray[copyArray.length / 2 - 1]) / 2);
            }

            //если количество элементов нечётное, то возвращаем средний элемент массива
            median = copyArray[copyArray.length / 2];
        for (int i = 0; i < copyArray.length; i++){
            for (int k = 0; k < copyArray.length; k++) {
                int buff;
                if (Math.abs(median-copyArray[i]) < Math.abs(median-copyArray[k]) ||
                        ((Math.abs(median-copyArray[i]) == Math.abs(median-copyArray[k])) & (median-copyArray[k]<0))){
                    buff = copyArray[i];
                    copyArray[i] = copyArray[k];
                    copyArray[k] = buff;
                }
            }
        }
        return copyArray;
    }
}
