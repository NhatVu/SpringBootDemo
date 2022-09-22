package com.example.demo.repositories;

import com.example.demo.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import util.AbstractIntegrationTest;

import java.util.ArrayList;

class UserRepositoryIntergrationTest extends AbstractIntegrationTest {
    @Autowired
    UserRepository userRepository;

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

}