package com.example.masproj.controllers;


import com.example.masproj.Repositories.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClubController {

    @Autowired
    ClubRepository clubRepository;


    @GetMapping("/kluby")
    public String home(Model model){
        model.addAttribute("clubs", clubRepository.findAll());
        return "ShowClubs";

    }
}
