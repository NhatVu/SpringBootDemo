package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/config")
public class ConfigController {
    @Value("${demo.name:default}")
    private String name;

    /*
    if using  @Value("${demo.fullname:full name is ${demo.name}}"), must have demo.name in property file
    If not, will throw error.
     */
    @Value("${demo.fullname:full name is ${demo.name}}")
    private String fullname;

    @Autowired
    private UserConfig userConfig;

    @GetMapping("name")
    public String getName(){
        return name;
    }

    @GetMapping("fullname")
    public String getFullname(){return fullname;}

    @GetMapping("bean")
    public String getConfigByBean(){
        return userConfig.toString();
    }

}
