package com.example.masproj.controllers;

import com.example.masproj.config.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {



    @GetMapping("/login")
    public String login(){

        return "loginView";

    }


}
