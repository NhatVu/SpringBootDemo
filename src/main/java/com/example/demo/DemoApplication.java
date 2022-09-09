package com.example.demo;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.filters.AuthFilter;
import com.example.demo.services.UserService;
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
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        source.registerCorsConfiguration("/**", config);
        registrationBean.setFilter(new CorsFilter(source));
        registrationBean.setOrder(0);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> filterRegistrationBean() {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        AuthFilter authFilter = new AuthFilter();
        registrationBean.setFilter(authFilter);
        registrationBean.addUrlPatterns("/api/categories/*");
        return registrationBean;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPPER_ADMIN"));

            userService.saveUser(new User(null, "a_firstname", "a_lastname", "a@gmail.com", "pass123", new ArrayList<>() ));
            userService.saveUser(new User(null, "b_firstname", "b_lastname", "b@gmail.com", "pass123", new ArrayList<>() ));
            userService.saveUser(new User(null, "c_firstname", "c_lastname", "c@gmail.com", "pass123", new ArrayList<>() ));
            userService.saveUser(new User(null, "d_firstname", "d_lastname", "d@gmail.com", "pass123", new ArrayList<>() ));
            userService.saveUser(new User(null, "e_firstname", "e_lastname", "e@gmail.com", "pass123", new ArrayList<>() ));
            userService.saveUser(new User(null, "f_firstname", "f_lastname", "f@gmail.com", "pass123", new ArrayList<>() ));
            userService.saveUser(new User(null, "g_firstname", "g_lastname", "g@gmail.com", "pass123", new ArrayList<>() ));
            userService.saveUser(new User(null, "nhat", "vu", "nhat@gmail.com", "pass123", new ArrayList<>() ));
            userService.saveUser(new User(null, "john", "vu", "john@gmail.com", "pass123", new ArrayList<>() ));

            userService.addRoleToUser("john@gmail.com", "ROLE_USER");
            userService.addRoleToUser("nhat@gmail.com", "ROLE_ADMIN");
            userService.addRoleToUser("nhat@gmail.com", "ROLE_USER");
        };
    }

}
