package com.javarush.task.task18.task1828;

/* 
Прайсы 2
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception{

                    switch (args[0]) {
                        case ("-u"): {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                            String fileName = reader.readLine();
                            reader.close();
                            ArrayList<String> list = new ArrayList<>();
                            BufferedReader reader1 = new BufferedReader(new FileReader(fileName));

                            while (reader1.ready()) {
                                list.add(reader1.readLine());
                            }
                            reader1.close();

                            String id = args[1].length() > 8 ? args[1].substring(0, 8) : String.format("%-8s", args[1]);
                            String productName = args[2].length() > 30 ? args[2].substring(0, 30) : String.format("%-30s", args[2]);
                            String price = args[args.length - 2].length() > 8 ? args[args.length - 2].substring(0, 8) : String.format("%-8s", args[3]);
                            String quantity = args[args.length - 1].length() > 4 ? args[args.length - 1].substring(0, 4) : String.format("%-4s", args[4]);

                            for(int i = 0; i < list.size(); i++) {
                                if(list.get(i).startsWith(id)) {
                                    list.set(i, id + productName + price + quantity);
                                    break;
                                }
                            }

                            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                            for (String s : list) {
                                writer.write(s);
                                writer.newLine();
                            }
                            writer.close();
                        }
                    case ("-d"): {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                        String fileName = reader.readLine();
                        reader.close();
                        ArrayList<String> list = new ArrayList<>();
                        BufferedReader reader1 = new BufferedReader(new FileReader(fileName));

                        while (reader1.ready()) {
                            list.add(reader1.readLine() + "\n");
                        }
                        reader1.close();

                            int idMax = Integer.parseInt(args[1]);
                            try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName))) {
                                for (String s : list) {
                                    if (list.isEmpty()) break;
                                    if (!(s.substring(0, 8).contains(String.valueOf(idMax)))) {
                                        fileWriter.write(s);
                                    }
                                }
                        } catch (IOException e) {
                            System.out.println("error");
                        }
                    }
                    }
        }
    }
