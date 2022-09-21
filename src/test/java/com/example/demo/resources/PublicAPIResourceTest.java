package com.example.demo.resources;

import com.example.demo.domain.User;
import com.example.demo.helper.JsonHelper;
import com.example.demo.helper.Utils;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import util.AbstractIntegrationTest;
import util.TestHelper;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PublicAPIResourceTest extends AbstractIntegrationTest {
    @Autowired
    protected TestRestTemplate restTemplate;

    @Autowired
    protected UserService userService;

    private HttpHeaders getPostHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @Test
    void testPublicAPI() {
    }


    @Test
    void loginUserSuccess() throws JsonProcessingException {
        // given
        String json = TestHelper.readFromResources("http/controller/public_controller_integration_test/login_admin.json");
        String email = "admin@gmail.com";

        // when
        ResponseEntity<String> response = restTemplate.exchange("/api/public/login",
                HttpMethod.POST,
                new HttpEntity<>(json, getPostHeaders()),
                String.class);

        // then
        assertThat(response.getStatusCodeValue()).as("Assert http status. expected 200").isEqualTo(HttpStatus.OK.value());

        String jsonResponse = response.getBody();

        JsonNode jsonMap = JsonHelper.parse(jsonResponse);
        Map dataMap = JsonHelper.fromJsonNode(jsonMap.get("data"), Map.class);
        String acutalToken = dataMap.get("token").toString();

        Map<String, Object> claims = Utils.verifyToken(acutalToken);
        int userId = Integer.parseInt(claims.get("userId").toString());

        User actualUser = userService.findById(userId);

        assertThat(actualUser.getEmail()).isEqualTo(email);
        assertThat(actualUser.getUserId()).isEqualTo(userId);
    }

    @Test
    void loginUserFail() throws JsonProcessingException {
        String json = TestHelper.readFromResources("http/controller/public_controller_integration_test/login_fail.json");
        String email = "admin@gmail.com";

        // when
        ResponseEntity<String> response = restTemplate.exchange("/api/public/login",
                HttpMethod.POST,
                new HttpEntity<>(json, getPostHeaders()),
                String.class);

        // then
        assertThat(response.getStatusCodeValue()).as("Assert http status. expected 403").isEqualTo(HttpStatus.FORBIDDEN.value());

        String jsonResponse = response.getBody();

        JsonNode jsonMap = JsonHelper.parse(jsonResponse);
        String error = jsonMap.get("error").get("content").asText();
        int status = jsonMap.get("status").asInt();

        assertThat(status).isEqualTo(-HttpStatus.NOT_FOUND.value());
        assertThat(error).isEqualTo("wrong email + password");
    }

    @Test
    void registerUser() {
    }
}