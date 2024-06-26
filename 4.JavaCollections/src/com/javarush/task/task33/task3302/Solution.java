package com.javarush.task.task33.task3302;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

/* 
Вторая сериализация в JSON
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Cat cat = new Cat();
        cat.name = "Murka";
        cat.age = 5;
        cat.weight = 3;

        StringWriter writer = new StringWriter();
        convertToJSON(writer, cat);
        System.out.println(writer.toString());
    }

    public static void convertToJSON(StringWriter writer, Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, object);
    }

    @JsonAutoDetect
    @JsonPropertyOrder(value = {"wildAnimal", "over"})
    public static class Cat {
        @JsonProperty(value = "wildAnimal")
        public String name;
        @JsonIgnore
        public int age;
        @JsonProperty(value = "over")
        public int weight;

        Cat() {
        }
    }
}
