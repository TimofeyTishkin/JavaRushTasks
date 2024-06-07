package com.javarush.task.task28.task2810;


import com.javarush.task.task28.task2810.model.HHStrategy;
import com.javarush.task.task28.task2810.model.Model;
import com.javarush.task.task28.task2810.model.MoikrugStrategy;
import com.javarush.task.task28.task2810.model.Provider;
import com.javarush.task.task28.task2810.view.HtmlView;

public class Aggregator {
    public static void main(String[] args) {
        HtmlView view = new HtmlView();
        HHStrategy strategy = new HHStrategy();

        Provider provider = new Provider(strategy);
        Provider provider1 = new Provider(new MoikrugStrategy());

        Model model = new Model(view, provider1, provider);

        Controller controller = new Controller(model);

        view.setController(controller);


        view.userCitySelectEmulationMethod();
    }
}
