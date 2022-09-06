package com.example.demo.repositories;

import com.example.demo.domain.User;
import com.example.demo.exception.EtAuthException;

//talking with db
public interface UserRepository {
    Integer create(String firstName, String lastName, String email, String password) throws EtAuthException;

    User findByEmailAndPassword(String email, String password) throws EtAuthException;

    Integer getCountByEmail(String email);

    User findById(Integer userId);
}
