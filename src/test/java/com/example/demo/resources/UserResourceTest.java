//package com.example.demo.resources;
//
//import org.junit.jupiter.api.Test;
//import org.skyscreamer.jsonassert.JSONAssert;
//import org.skyscreamer.jsonassert.JSONCompareMode;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import util.AbstractIntegrationTest;
//import util.TestHelper;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//
//class UserResourceTest extends AbstractIntegrationTest {
//    @Autowired
//    protected TestRestTemplate restTemplate;
//
//    @Test
//    void getAllUser() {
//    }
//
//    @Test
//    void findUserById() {
//        // given
//
//        // when
//        ResponseEntity<String> response = restTemplate.getForEntity("/api/users/1", String.class);
//
//        // then
//        String etag = response.getHeaders().getETag();
//        assertThat(etag).isNotEmpty();
//        assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
//        String expected = TestHelper.readFromResources("http/controller/account_controller_integration_test/get_account_dto.json");
//        JSONAssert.assertEquals(expected, response.getBody(), JSONCompareMode.LENIENT);
//    }
//
//    @Test
//    void addRoleToUser() {
//    }
//}