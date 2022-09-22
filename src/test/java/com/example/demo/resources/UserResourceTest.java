package com.example.demo.resources;

import com.example.demo.domain.User;
import com.example.demo.helper.JsonHelper;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import util.AbstractIntegrationTest;
import util.TestHelper;

class UserResourceTest extends AbstractIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserService userService;
    @Test
    void getAllUser_UserRole() throws JsonProcessingException {
        // given
        String json = TestHelper.readFromResources("http/controller/public_controller_integration_test/login_user.json");
        String token = getTokenFromLogin(json);

        // when
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        ResponseEntity<String> response = restTemplate.exchange("/api/users/",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class);

        // expected
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        String jsonResponse = response.getBody();

        JsonNode jsonNode = JsonHelper.parse(jsonResponse);
        User[] data = JsonHelper.fromJsonNode(jsonNode.get("data"), User[].class);

        assertThat(data).isNotNull().isNotEmpty();
        assertThat(data.length).isEqualTo(3); // default offset and limit

    }

    @Test
    void getAllUser_AdminRole(){

    }

    @Test
    @DisplayName("Check get all user with normal role. Expected throw unauthorized request")
    void getAllUser_NormRole() throws JsonProcessingException {
        // given
        String json = TestHelper.readFromResources("http/controller/public_controller_integration_test/login_normal.json");
        String token = getTokenFromLogin(json);

        // when
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        ResponseEntity<String> response = restTemplate.exchange("/api/users/",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class);

        // expected
        String jsonResponse = response.getBody();
        System.out.println("json response: " + jsonResponse);
        assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.FORBIDDEN.value());
    }

    @Test
    void findUserById_UserRole() {
    }

    @Test
    void addRoleToUser() {
    }

    public String getTokenFromLogin(String loginJson) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> response = restTemplate.exchange("/api/public/login",
                HttpMethod.POST, new HttpEntity<>(loginJson, headers), String.class );
        assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());

        String body = response.getBody();
        JsonNode bodyNode = JsonHelper.parse(body);
        String token = bodyNode.get("data").get("token").asText();
        assertThat(token).isNotNull();
        return token;
    }
}