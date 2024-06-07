package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;


public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader readerFileName = new BufferedReader(new InputStreamReader(System.in));
        String filename = readerFileName.readLine();
        readerFileName.close();

        FileReader fReader = new FileReader(filename);
        StringBuffer sb = new StringBuffer();
        while (fReader.ready())
            sb.append((char)fReader.read());
        fReader.close();

        String allFromFile = sb.toString();
 
        Document doc = Jsoup.parse(allFromFile,"", Parser.xmlParser());
        Elements elements = doc.getElementsByTag(args[0]);
        for (Element element : elements) {
            System.out.println(element);
        }
    }
}
