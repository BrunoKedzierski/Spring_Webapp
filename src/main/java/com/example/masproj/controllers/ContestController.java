package com.example.masproj.controllers;

import com.example.masproj.Repositories.ContestRepository;
import com.example.masproj.Repositories.EntryRepository;
import com.example.masproj.Repositories.FighterRepository;
import com.example.masproj.Services.ContestService;
import com.example.masproj.Services.EntryService;
import com.example.masproj.config.SecurityUser;
import com.example.masproj.model.Contest;
import com.example.masproj.model.Entry;
import com.example.masproj.model.Fighter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ContestController {

    @Autowired
    ContestRepository contestRepository;

    @Autowired
    FighterRepository fighterRepository;

    @Autowired
    EntryRepository entryRepository;

    @Autowired
    EntryService entryService;

    @Autowired
    ContestService contestService;

@GetMapping("/zawody")
    public String showConests(Model model){
     List<Contest> contests = contestRepository.findAll();

     model.addAttribute("contests",contests);
     return "ShowContests";
}



    @GetMapping("/mojezawody")
    public String showMyConests(Authentication authentication, Model model){
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        Fighter fighter = fighterRepository.findById(securityUser.getId()).get();
        List<Contest>  contests= contestRepository.findAllByEntriesFighter(fighter).get();
        model.addAttribute("contests",contests);
        return "ShowMyContests";
    }


    @PreAuthorize("hasRole('ROLE_Fighter')")
    @PostMapping("/wyrejestruj")
    public String unRegister(@RequestParam("contestId") Long contestId, Authentication authentication, RedirectAttributes redirectAttributes){
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        Fighter fighter = fighterRepository.findById(securityUser.getId()).get();

        String message = entryService.unregister(fighter,contestId);

        if(!message.isEmpty()){
            redirectAttributes.addFlashAttribute("UnregisterError", message);
            return "redirect:/mojezawody";
        }
        return "redirect:/mojezawody";

    }




    @PreAuthorize("hasRole('ROLE_Fighter')")
    @PostMapping("/rejestruj")
    public String register(RedirectAttributes redirectAttributes, @ModelAttribute("contest") Contest contest, BindingResult result, Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        Fighter fighter = fighterRepository.findById(securityUser.getId()).get();

        String status = entryService.enterContest(fighter, contest);

        if (!status.isEmpty()) {
            redirectAttributes.addFlashAttribute("EntryError", status);
        }

        return "redirect:/zawody/" + contest.getId();
    }


    @PreAuthorize("hasRole('ROLE_Fighter')")
    @GetMapping("/zawody/{id}")
    public String getContestById(@PathVariable long id, Model model) {
        Contest contest = contestRepository.findById(id).get();
        List<Entry> entries = entryRepository.findAllByContest(contest);
        List<Fighter> fighters = new ArrayList<>();

        if (!entries.isEmpty()) {
            for (Entry e : entries) {
                fighters.add(fighterRepository.findById(e.getFighter().getId()).get());
            }
        }

        model.addAttribute("fighters", fighters);
        model.addAttribute("contest", contest);


        String message = (String) model.asMap().get("EntryError");
        if (message != null) {
            model.addAttribute("EntryError", message);
        }

        return "ContestDetails";
    }
}
