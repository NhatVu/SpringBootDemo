package com.example.demo.resources;

import com.example.demo.domain.User;
import com.example.demo.helper.JsonHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Arrays;

public class helpTest {
    public static void main(String[] args) throws JsonProcessingException {
        String json = "{\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"userId\": 15,\n" +
                "            \"firstName\": \"onlyadmin\",\n" +
                "            \"lastName\": \"vu\",\n" +
                "            \"email\": \"onlyadmin@gmail.com\",\n" +
                "            \"password\": \"$2a$10$oYR52ucyUBPZQDqWn9b5/um.g2WaLzrnNnps9Nxac9jdm1MeIT9mW\",\n" +
                "            \"roles\": [\n" +
                "                {\n" +
                "                    \"id\": 1,\n" +
                "                    \"name\": \"ROLE_ADMIN\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 14,\n" +
                "            \"firstName\": \"normal\",\n" +
                "            \"lastName\": \"vu\",\n" +
                "            \"email\": \"normal@gmail.com\",\n" +
                "            \"password\": \"$2a$10$EjCCHETu7rNUnMunH2EYgOzcFPmb7fbSR4FKb1bZeKiufPDOQXqwi\",\n" +
                "            \"roles\": []\n" +
                "        },\n" +
                "        {\n" +
                "            \"userId\": 13,\n" +
                "            \"firstName\": \"admin\",\n" +
                "            \"lastName\": \"vu\",\n" +
                "            \"email\": \"admin@gmail.com\",\n" +
                "            \"password\": \"$2a$10$TNbBj5dhFUVeIE20jav0buvep4R0k3M9I8z8FqZDtrBxj1Mxypt9W\",\n" +
                "            \"roles\": [\n" +
                "                {\n" +
                "                    \"id\": 1,\n" +
                "                    \"name\": \"ROLE_ADMIN\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 2,\n" +
                "                    \"name\": \"ROLE_USER\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"status\": 200\n" +
                "}";
        JsonNode node = JsonHelper.parse(json);
        JsonNode data = node.get("data");
        User[] users = JsonHelper.fromJsonNode(data, User[].class);
        Arrays.stream(users).forEach(System.out::println);
        System.out.println("users: " + users);
    }
}
