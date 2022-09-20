package com.example.demo;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.filters.AuthFilter;
import com.example.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;

@SpringBootApplication
@Slf4j
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }

//    @Bean
//    public FilterRegistrationBean<CorsFilter> corsFilter() {
//        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        source.registerCorsConfiguration("/**", config);
//        registrationBean.setFilter(new CorsFilter(source));
//        registrationBean.setOrder(0);
//        return registrationBean;
//    }

//    @Bean
//    public FilterRegistrationBean<AuthFilter> filterRegistrationBean() {
//        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
//        AuthFilter authFilter = new AuthFilter();
//        registrationBean.setFilter(authFilter);
//        registrationBean.addUrlPatterns("/api/categories/*");
//        return registrationBean;
//    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            log.info("Run CommandLineRunner");
            userService.saveRole(new Role(null, Constants.ROLE_ADMIN));
            userService.saveRole(new Role(null, Constants.ROLE_USER));

            userService.saveUser(new User(null, "a_firstname", "a_lastname", "a@gmail.com", "pass123", new ArrayList<>() ));
            userService.saveUser(new User(null, "b_firstname", "b_lastname", "b@gmail.com", "pass123", new ArrayList<>() ));
            userService.saveUser(new User(null, "c_firstname", "c_lastname", "c@gmail.com", "pass123", new ArrayList<>() ));
            userService.saveUser(new User(null, "d_firstname", "d_lastname", "d@gmail.com", "pass123", new ArrayList<>() ));
            userService.saveUser(new User(null, "e_firstname", "e_lastname", "e@gmail.com", "pass123", new ArrayList<>() ));
            userService.saveUser(new User(null, "f_firstname", "f_lastname", "f@gmail.com", "pass123", new ArrayList<>() ));
            userService.saveUser(new User(null, "g_firstname", "g_lastname", "g@gmail.com", "pass123", new ArrayList<>() ));
            userService.saveUser(new User(null, "nhat", "vu", "nhat@gmail.com", "pass123", new ArrayList<>() ));
            userService.saveUser(new User(null, "john", "vu", "john@gmail.com", "pass123", new ArrayList<>() ));
            userService.saveUser(new User(null, "user", "vu", "user@gmail.com", "pass123", new ArrayList<>() ));
            userService.saveUser(new User(null, "admin", "vu", "admin@gmail.com", "pass123", new ArrayList<>() ));
            userService.saveUser(new User(null, "normal", "vu", "normal@gmail.com", "pass123", new ArrayList<>() ));
            userService.saveUser(new User(null, "onlyadmin", "vu", "onlyadmin@gmail.com", "pass123", new ArrayList<>() ));


            userService.addRoleToUser("john@gmail.com", Constants.ROLE_USER);
            userService.addRoleToUser("nhat@gmail.com", Constants.ROLE_ADMIN);
            userService.addRoleToUser("nhat@gmail.com", Constants.ROLE_USER);

            userService.addRoleToUser("user@gmail.com", Constants.ROLE_USER);

            userService.addRoleToUser("admin@gmail.com", Constants.ROLE_ADMIN);
            userService.addRoleToUser("admin@gmail.com", Constants.ROLE_USER);

            userService.addRoleToUser("onlyadmin@gmail.com", Constants.ROLE_ADMIN);
        };
    }

}
