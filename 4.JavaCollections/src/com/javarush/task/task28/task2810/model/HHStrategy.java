package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HHStrategy implements Strategy{
    private static final String URL_FORMAT =
            "http://hh.ua/search/vacancy?text=java+%s&page=%d";
    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> list = new ArrayList<>();
        Vacancy vacancy;
        int i = 0;
        Document doc;
        try {
            while (true) {

                doc = getDocument(searchString, i++);
                if (doc == null) {
                    break;
                }
                Elements elements = doc.select("[data-qa=vacancy-serp__vacancy]");
                if (elements.size() == 0) {
                    break;
                }
                for (Element elem : elements) {
                    vacancy = new Vacancy();
                    vacancy.setTitle(elem.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").text());
                    vacancy.setCity(elem.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").text());
                    vacancy.setCompanyName(elem.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text());
                    vacancy.setSalary(elem.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").text());

                    vacancy.setUrl(elem.getElementsByAttributeValueContaining("data-qa", "title").attr("href"));
                    vacancy.setSiteName(URL_FORMAT);
                    list.add(vacancy);
                }
            }
        } catch (IOException ignore) {
        }
        return list;
    }

    protected Document getDocument(String searchString, int page) throws IOException{
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page)).data("java", "Киев")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36")
                    .referrer("no-referrer-when-downgrade").get();
    }
}
