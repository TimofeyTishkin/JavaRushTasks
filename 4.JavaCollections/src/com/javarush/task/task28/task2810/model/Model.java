package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.view.View;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class Model {
    View view;
    Provider[] providers;

    public Model(View view, Provider...providers) {
        if(providers == null || view== null) throw new IllegalArgumentException();
        this.view = view;
        this.providers = providers;
    }

    public void selectCity(String city){
        List<Vacancy> allVac = new ArrayList<>();
        for(Provider provider : providers){
            allVac.addAll(provider.getJavaVacancies(city));
        }
        view.update(allVac);
    }
}
