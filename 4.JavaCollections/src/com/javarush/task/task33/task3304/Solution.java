package com.javarush.task.task33.task3304;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

/* 
Конвертация из одного класса в другой используя JSON Ӏ 3304
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Second s = (Second) convertOneToAnother(new First(), Second.class);
        First f = (First) convertOneToAnother(new Second(), First.class);
        System.out.println(f instanceof First);
        System.out.println(s instanceof Second);
    }

    public static Object convertOneToAnother(Object one, Class<?> resultClassObject) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        mapper.writeValue(writer,one);
        String str = writer.toString().replaceFirst(one.getClass().getSimpleName().toLowerCase(),resultClassObject.getSimpleName().toLowerCase());

        writer.close();
        return mapper.readValue(str, resultClassObject);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "className")
    @JsonSubTypes(@JsonSubTypes.Type(value = First.class, name = "first"))
    public static class First {
        public int i;
        public String name;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "className")
    @JsonSubTypes(@JsonSubTypes.Type(value = Second.class, name = "second"))
    public static class Second {
        public int i;
        public String name;
    }
}
