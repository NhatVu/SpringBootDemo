package com.example.demo.services;

import com.example.demo.domain.User;
import com.example.demo.exception.EtAuthException;
import com.example.demo.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUser(int page, int pageSize){
        List<User> res = new ArrayList<>();
        Pageable sortedByName =
                PageRequest.of(page, pageSize, Sort.by("userId").descending()); // Sort.by("user_id").descending()
        userRepository.findAll(sortedByName).forEach(res::add);
        return res;
    }

    @Override
    public User validateUser(String email, String password) throws EtAuthException {
        if (email != null )
            email = email.toLowerCase();
        logger.info("email=" + email + ", password=" + password);
        List<User> users = userRepository.findByEmailAndPassword(email, password);
        logger.info("users: " + users);
        if (users.size() > 0){
            return users.get(0);
        }
        return null;
    }

    @Override
    public User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(email != null) email = email.toLowerCase();
        if(!pattern.matcher(email).matches())
            throw new EtAuthException("Invalid email format");
        long count = userRepository.countByEmail(email);
        logger.info("countByEmail: "+ count);
        if(count > 0)
            throw new EtAuthException("Email already in use");
        // fix primary key for User. Have use external service to generate id
        User user = new User(firstName, lastName, email, password);
        User res = userRepository.save(user);
        return res;
    }
}
