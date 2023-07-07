package com.example.demo.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "demo")
@ToString
@Setter
@Getter
public class UserConfig {
    private String name;
    private String fullname;
    private List<String> group;


}
