package com.example.demo.helper;

import com.example.demo.pojo.LoginPOJO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class JsonHelperTest {
    String jsonSource = "{\n" +
            "  \"email\": \"admin@gmail.com\",\n" +
            "  \"password\": \"pass123\",\n" +
            "  \"date\": \"2022-12-20\"\n" +
            "}";

    @Test
    public void parse() throws JsonProcessingException {
//        String jsonSource = "{\n" +
//                "  \"email\": \"admin@gmail.com\",\n" +
//                "  \"password\": \"pass123\"\n" +
//                "}";
        JsonNode node = JsonHelper.parse(jsonSource);
        Assertions.assertThat( node.get("email").asText()).isEqualTo("admin@gmail.com");
    }

    @Test
    void fromJson() throws JsonProcessingException {
        JsonNode node = JsonHelper.parse(jsonSource);
        LoginPOJO pojo = JsonHelper.fromJsonNode(node, LoginPOJO.class);
        System.out.println("Date: " + pojo.getDate());
        Assertions.assertThat("admin@gmail.com").isEqualTo(pojo.getEmail());
        Assertions.assertThat("2022-12-20").isEqualTo(pojo.getDate().toString());
    }

    @Test
    void toJson(){
        LoginPOJO pojo = new LoginPOJO();
        pojo.setEmail("a@gmail.com");

        JsonNode node = JsonHelper.toJson(pojo);

        Assertions.assertThat(node.get("email").asText()).isEqualTo("a@gmail.com");
    }

    @Test
    void stringify() throws JsonProcessingException {
        LoginPOJO pojo = new LoginPOJO();
        pojo.setEmail("a@gmail.com");

        JsonNode node = JsonHelper.toJson(pojo);

        String jsonStr = JsonHelper.stringify(node);
        System.out.println(jsonStr);

        System.out.println(JsonHelper.prettyStringify(node));
    }
}