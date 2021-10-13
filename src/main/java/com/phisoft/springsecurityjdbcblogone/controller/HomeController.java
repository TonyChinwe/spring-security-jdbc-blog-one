package com.phisoft.springsecurityjdbcblogone.controller;

import com.phisoft.springsecurityjdbcblogone.security.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private JdbcUserDetailsManager jdbcUserDetailsManager;
    private PasswordEncoder passwordEncoder;
    public HomeController(JdbcUserDetailsManager jdbcUserDetailsManager,PasswordEncoder passwordEncoder){
        this.jdbcUserDetailsManager=jdbcUserDetailsManager;
        this.passwordEncoder=passwordEncoder;
    }
    @GetMapping("/hello")
    public String hello(){
        return "Hello world";
    }

    @PostMapping("/save")
    public String registerUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        jdbcUserDetailsManager.createUser(user);
        return "User successfully created";
    }

}
