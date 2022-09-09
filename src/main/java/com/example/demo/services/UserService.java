package com.example.demo.services;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.exception.EtAuthException;

import java.util.List;

public interface UserService {
    List<User> getAllUser(int page, int pageSize);

    User findById(int userId);
    User validateUser(String email, String password) throws EtAuthException;
    User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException;

    User saveUser(User user);
    Role saveRole(Role role);
    List<Role> getALlRoles();

    void addRoleToUser(String email, String role);
}
