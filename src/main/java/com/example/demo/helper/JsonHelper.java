package com.example.demo.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.Map;

public class JsonHelper {
    // ObjectMapper is threadsafe
    private static ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper(){
        ObjectMapper defaultObject = new ObjectMapper();
        //
        defaultObject.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //support parsing pojo if pojo class is missing property
        defaultObject.registerModule(new JavaTimeModule()); // support java8 LocalDate
        return defaultObject;
    }

    public static JsonNode parse(String src) throws JsonProcessingException {
        return objectMapper.readTree(src);
    }

    public static <T> T parse(String src, Class<T> clazz) throws JsonProcessingException {
        JsonNode node = objectMapper.readTree(src);
        return fromJsonNode(node, clazz);
    }

    public static <T> T fromJsonNode(JsonNode node, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.treeToValue(node, clazz);
    }

    public static JsonNode toJson(Object a){
        return objectMapper.valueToTree(a);
    }

    public static String stringify(JsonNode node) throws JsonProcessingException {
        return generateString(node, false);
    }

    public static String prettyStringify(JsonNode node) throws JsonProcessingException {
        return generateString(node, true);
    }

    private static String generateString(JsonNode node, boolean pretty) throws JsonProcessingException {
        ObjectWriter writer = objectMapper.writer();
        if(pretty){
            writer = writer.with(SerializationFeature.INDENT_OUTPUT);
        }
        return writer.writeValueAsString(node);
    }

    public static void main(String[] args) throws JsonProcessingException {
        String jsonStr = "{\n" +
                "    \"data\": {\n" +
                "        \"token\": \"ey2iM\"\n" +
                "    },\n" +
                "    \"status\": 200\n" +
                "}";
        JsonNode jsonNode = parse(jsonStr);
        System.out.println("status: " + jsonNode.get("status").asText());
//        System.out.println("data.token: " + jsonNode.get("datal").get("token").asText());
        Map<String, String> data = fromJsonNode(jsonNode.get("data1"), Map.class);
        System.out.println("data: " + fromJsonNode(jsonNode.get("data1"), Map.class));
    }

}
