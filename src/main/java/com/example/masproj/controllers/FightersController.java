package com.example.masproj.controllers;


import com.example.masproj.Repositories.FighterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FightersController {

    @Autowired
    FighterRepository fighterRepository;


    @PreAuthorize("hasRole('ROLE_Delegate')")
    @GetMapping("/zawodnicy")
    public String home(Model model){
        model.addAttribute("fighters", fighterRepository.findAll());
        return "ShowFighters";

    }
}
