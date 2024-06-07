package com.javarush.task.task28.task2810.view;


import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class HtmlView implements View {
    private final String filePath = "./4.JavaCollections/src/" + this.getClass()
            .getPackage().getName().replace('.', '/') + "/vacancies.html";

    private Controller controller;

    public void userCitySelectEmulationMethod(){
        controller.onCitySelect("Odessa");
    }

    @Override
    public void update(List<Vacancy> vacancies) {
        System.out.println(filePath);
        try {
            updateFile(getUpdatedFileContent(vacancies));
        } catch (Exception ig) {
            ig.printStackTrace();
        }
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        Document vacanciesDocument;
        try {
            vacanciesDocument = getDocument();

            Element tempOrig = vacanciesDocument.getElementsByClass("template").first();
            Element eTmp = tempOrig.clone();
            eTmp.removeAttr("style");
            eTmp.removeClass("template");

            vacanciesDocument.select("tr[class=vacancy]").remove();

            for (Vacancy vac : vacancies) {
                Element eCopy = eTmp.clone();

                eCopy.getElementsByClass("city").first().text(vac.getCity());
                eCopy.getElementsByClass("companyName").first().text(vac.getCompanyName());
                eCopy.getElementsByClass("salary").first().text(vac.getSalary());

                Element link = eCopy.getElementsByTag("a").first();
                link.text(vac.getTitle());
                link.attr("href", vac.getUrl());

                tempOrig.before(eCopy.outerHtml());

            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Some exception occurred";
        }
        return vacanciesDocument.html();
    }

    private void updateFile(String str) {
        File file = new File(filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))){
            writer.write(str);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Document getDocument() throws IOException{
        return Jsoup.parse(new File(filePath), "UTF-8");
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
}
