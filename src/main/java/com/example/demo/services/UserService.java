package com.example.demo.services;

import com.example.demo.domain.User;
import com.example.demo.exception.EtAuthException;

import java.util.List;

public interface UserService {
    List<User> getAllUser(int page, int pageSize);
    User validateUser(String email, String password) throws EtAuthException;
    User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException;
}
