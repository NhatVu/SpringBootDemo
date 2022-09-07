package com.example.demo.repositories;

import com.example.demo.domain.User;
import com.example.demo.exception.EtAuthException;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//talking with db
public interface UserRepository extends CrudRepository<User, Integer> {
//    Integer create(String firstName, String lastName, String email, String password) throws EtAuthException;
    // define method following by JPQL -> JPA will generate the implementation.
    List<User> findByEmailAndPassword(String email, String password);

    long countByEmail(String email);
}
