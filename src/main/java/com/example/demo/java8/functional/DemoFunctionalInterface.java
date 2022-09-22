package com.example.demo.java8.functional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class DemoFunctionalInterface {
    public static void main(String[] args) {
//        Function<String, Integer> func = x -> x.length();
//
//        Integer apply = func.apply("mkyong");   // 6
//
//        System.out.println(apply);
        Function<String, Integer> func = x -> x.length();
        Function<Integer, Integer> func2 = x -> x * 2;
        Integer apply = func.apply("mkyong");
        System.out.println(apply);

        // chain
        System.out.println(func.andThen(func2).apply("mkyong"));

        List<String> list = Arrays.asList("node", "c++", "java", "javascript");
//        // using lambda
//        Map<String, Integer> map = convertListToMap(list, x -> x.length());
//        System.out.println(map);
//
//        // using String method reference
//        Map<String, Integer> map2 = convertListToMap(list, String::length);
//        System.out.println(map2);
//
//        Map<String, Integer> map3 = convertListToMap(list, DemoFunctionalInterface::getLength);
//        System.out.println(map3);

        list.forEach(System.out::println);
    }

    public static <T, R> Map<T, R> convertListToMap(List<T> list, Function<T, R> func){
        Map<T, R> result = new HashMap<>();
        for (T t: list){
            result.put(t, func.apply(t));
        }
        return result;
    }

    public static int getLength(String str){
        return str.length();
    }
}
