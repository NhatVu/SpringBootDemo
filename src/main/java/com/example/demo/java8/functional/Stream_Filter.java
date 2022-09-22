package com.example.demo.java8.functional;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Stream_Filter {
    public static void main(String[] args) {
        map_uppercase();
    }
    public static void map_uppercase(){
        List<String> alpha = Arrays.asList("a", "b", "c", "d");
        Function<String, String> func = x -> x.toUpperCase();
        List<String> upper = alpha.stream()
                .map(func)
                .collect(Collectors.toList());
        upper.forEach(System.out::println);
    }
    public static void filter_map(){
        List<Person> persons = Arrays.asList(
                new Person("mkyong", 30),
                new Person("jack", 20),
                new Person("lawrence", 40)
        );

        String p1_name = persons.stream().
                filter(x -> "jack".equals(x.getName()))
                .map(Person::getName)
                .findAny()
                .orElse(null);
        System.out.println("person 1: " + p1_name);

        String p2_name = persons.stream().
                filter(x -> "ahmook".equals(x.getName()))
                .map(x -> x.getName())
                .findAny()
                .orElse(null);
        System.out.println("person 2: " + p2_name);
    }

    public static void filter(){
        List<String> lines = Arrays.asList("spring", "node", "mkyong");
        List<String> result = lines.stream()
                .filter(line -> !"mkyong".equals(line))
                .collect(Collectors.toList()); // collect the output and convert stream to list

        result.forEach(System.out::println);
    }

    public static void findAny(){
        List<Person> persons = Arrays.asList(
                new Person("mkyong", 30),
                new Person("jack", 20),
                new Person("lawrence", 40)
        );

        Person p1 = persons.stream().
                filter(x -> "jack".equals(x.getName()))
                .findAny()
                .orElse(null);
        System.out.println("person 1: " + p1);

        Person p2 = persons.stream().
                filter(x -> "ahmook".equals(x.getName()))
                .findAny()
                .orElse(null);
        System.out.println("person 2: " + p2);
    }


}
