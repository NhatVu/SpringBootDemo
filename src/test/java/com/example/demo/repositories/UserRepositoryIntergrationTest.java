package com.example.demo.repositories;

import com.example.demo.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import util.AbstractIntegrationTest;

import java.util.ArrayList;

//@Testcontainers
//@SpringBootTest
class UserRepositoryIntergrationTest extends AbstractIntegrationTest {
    @Autowired
    UserRepository userRepository;

//    @Container
//    public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:14.5")
//            .withUsername("duke")
//            .withPassword("password")
//            .withDatabaseName("test");
//
//    @DynamicPropertySource
//    static void properties(DynamicPropertyRegistry registry){
//        registry.add("spring.datasource.url", container::getJdbcUrl);
//        registry.add("spring.datasource.password", container::getPassword);
//        registry.add("spring.datasource.username", container::getUsername);
//    }

    @Test
    void findByEmailAndPassword() {
    }

    @Test
    void addUser(){
        int previousTotal = 13;
        Assertions.assertThat(userRepository.count()).isEqualTo(previousTotal);
        User user = new User(null, "m_firstname", "m_lastname", "m@gmail.com", "pass123", new ArrayList<>() );
        userRepository.save(user);

        Assertions.assertThat(userRepository.count()).isEqualTo(previousTotal + 1);
    }

    @Test
    void findByEmail() {
        System.out.println("context load");


        String email = "a@gmail.com";
        User foundObject = userRepository.findByEmail(email);

        Assertions.assertThat(foundObject.getEmail()).isEqualTo(email);
    }

    @Test
    void countByEmail() {
        String email = "a@gmail.com";
        User foundObject = userRepository.findByEmail(email);

        Assertions.assertThat(foundObject.getEmail()).isEqualTo(email);
    }
}