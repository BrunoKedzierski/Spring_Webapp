package com.example.masproj.controllers;

import com.example.masproj.config.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainMenu {



    @GetMapping("/menu")
    public String home(Authentication auhtentication, Model model){

        SecurityUser securityUser = (SecurityUser)  auhtentication.getPrincipal();

        String role = securityUser.getAuthorities().toArray()[0].toString();



        System.out.println(role);
        if(role.equals("ROLE_Fighter"))
            return "FighterMenuView";
        else
            return "DelegateMenuView";

    }


}
