package com.example.demo.services;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.exception.EtAuthException;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUser(int page, int pageSize){
        List<User> res = new ArrayList<>();
        Pageable sortedByName =
                PageRequest.of(page, pageSize, Sort.by("userId").descending()); // Sort.by("user_id").descending()
        userRepository.findAll(sortedByName).forEach(res::add);
        return res;
    }

    @Override
    public User findById(int userId){
        User res = userRepository.findById(userId).orElse(null);
        return res;
    }

    @Override
    public User validateUser(String email, String password) throws EtAuthException {
        if (email != null )
            email = email.toLowerCase();
        User user = userRepository.findByEmail(email);
        if (user == null){
            return null;
        }
        if (!passwordEncoder.matches(password, user.getPassword())){
            return null;
        };
        return user;
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

    @Override
    public User saveUser(User user) {
        logger.info("Save new user to the database", user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        logger.info("Save new role to the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getALlRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        logger.info("Adding role {} to user {}", roleName, email);
        User user = userRepository.findByEmail(email);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // the function belongs to Interface, can't change. Treat username as email
        User user = userRepository.findByEmail(username);
        if (user == null){
            logger.error("User not found in the database");
            throw new UsernameNotFoundException(("User not found in database"));
        }else{
            logger.info("User found. email {}, password: {}", username, user.getPassword());
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
