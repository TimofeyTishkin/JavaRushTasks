package com.javarush.task.task18.task1827;

/* 
Прайсы
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {
        String id, productName, price, quantity;
        ArrayList<String> saveFile = new ArrayList<>();
        try {
            BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
            String nameFile = reader.readLine();
            BufferedReader fileReader = new BufferedReader(new FileReader(nameFile));
            BufferedWriter fileWriter;
            while(fileReader.ready()){
                saveFile.add(fileReader.readLine()+"\n");
            }



            if (args[0].equals("-c") && args.length == 4) {

                productName = args[1];
                productName = addSpace(productName, 30-productName.length());
                if(productName.length()>30)productName = productName.substring(0,30);
                price = args[2];
                price = addSpace(price,8-price.length());
                if(price.length()>8)price = price.substring(0,8);
                quantity = args[3];
                quantity = addSpace(quantity, 4-quantity.length());
                if(quantity.length()>4)quantity = quantity.substring(0,4);

                int idMax = 0;
                for(String s : saveFile){
                    if(saveFile.isEmpty()) break;

                    s = s.substring(0,8);
                    if(-1 != s.indexOf(" ")) s = s.substring(0,s.indexOf(" "));
                    int newid = Integer.parseInt(s);
                    if(newid>idMax){
                        idMax = newid;
                    }
                }
                idMax++;
                id = "" + idMax;
                id = addSpace(id, 8-id.length());

                fileWriter = new BufferedWriter(new FileWriter(nameFile));
                String newString =  id + productName + price + quantity;
                saveFile.add(newString);

                for(String s: saveFile){
                    fileWriter.write(s);
                }
                fileWriter.close();

            }
            reader.close();
            fileReader.close();

        }catch(Exception e){
            e.printStackTrace();
        }


    }
    private static String addSpace(String s, int i ){

        for(int x = 0; x<i; x++){
            s = s+" ";
        }
        return s;
    }
}


