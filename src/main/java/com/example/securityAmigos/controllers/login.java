package com.example.securityAmigos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class login {
    @GetMapping("/login")
    public String getMapping(){
        return "login";
    }
}
