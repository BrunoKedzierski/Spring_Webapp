package com.example.masproj.controllers;


import com.example.masproj.Repositories.FighterRepository;
import com.example.masproj.Services.LicenceService;
import com.example.masproj.config.SecurityUser;
import com.example.masproj.model.Fighter;
import com.example.masproj.model.Licence;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class LicenceController {

    @Autowired
    LicenceService licenceService;

    @Autowired
    FighterRepository fighterRepository;

    @PreAuthorize("hasRole('ROLE_Fighter')")
    @GetMapping("/mojelicencje")
    public String mylicences(Model model, Authentication authentication){
        SecurityUser securityUser = (SecurityUser)  authentication.getPrincipal();

        Fighter fighter = fighterRepository.findByEmail(securityUser.getUsername()).get();
        List<Licence> licences =  licenceService.getLicencesForFigther(fighter).get();

        String errorMessage = (String) model.asMap().get("LicenceError");
        if (errorMessage != null) {
            model.addAttribute("LicenceError", errorMessage);
        }

        String licenceSuccess = (String) model.asMap().get("LicenceSuccess");
        if (licenceSuccess != null) {
            model.addAttribute("LicenceSuccess", licenceSuccess);
        }


        model.addAttribute("licences", licences);
        return "Showlicences";

    }

    @PreAuthorize("hasRole('ROLE_Delegate')")
    @GetMapping("/licencja/{id}")
    public String getLicenceById(@PathVariable long id, Model model) {
        Licence licence = licenceService.getLicenceById(id).get();
        Fighter fighter = licence.getFighter();
        model.addAttribute(licence);
        model.addAttribute(fighter);



        String errorMessage = (String) model.asMap().get("LicenceError");
        if (errorMessage != null) {
            model.addAttribute("LicenceError", errorMessage);
        }

        String licenceSuccess = (String) model.asMap().get("LicenceSuccess");
        if (errorMessage != null) {
            model.addAttribute("LicenceSuccess", licenceSuccess);
        }

        return "LicenceDetail";
    }

    @PreAuthorize("hasRole('ROLE_Delegate')")
    @PostMapping("/licencja")
    public String updateLicence( @ModelAttribute("licence") Licence licence, RedirectAttributes redirectAttributes) {

        System.out.println("Updating licence");

        System.out.println(licence.getStatus());
        Licence licenceToUpdate = licenceService.getLicenceById(licence.getId()).get();

        licenceToUpdate.setStatus(licence.getStatus());
        licenceToUpdate.setBeginsOn(licence.getBeginsOn());
        licenceToUpdate.setEndsOn(licence.getEndsOn());
        String status =  licenceService.assignLicence(licenceToUpdate);

        if (!status.isEmpty()) {
            redirectAttributes.addFlashAttribute("LicenceError", status);
            return   "redirect:/licencja/" + licence.getId();
        }
            redirectAttributes.addFlashAttribute("LicenceSuccess", "Licencja zostala zakutalizowana!");

       return   "redirect:/licencja/" + licence.getId() ;
    }


    @PreAuthorize("hasRole('ROLE_Delegate')")
    @GetMapping("/licencje")
    public String alllicences(Model model, Authentication authentication){


        model.addAttribute("licences", licenceService.getAllLicences());
        return "ShowAllLicences";

    }


    @PreAuthorize("hasRole('ROLE_Fighter')")
    @GetMapping("/wystap")
    public String addLicence(Model model){

        model.addAttribute("licence", new Licence());
        return "LicenceSubmissionForm";

    }


    @PreAuthorize("hasRole('ROLE_Fighter')")
    @PostMapping("/wystap")
    public String addLicence(@Valid Licence licence, BindingResult result, Authentication authentication, RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            return "LicenceSubmissionForm";
        }


        SecurityUser securityUser = (SecurityUser)  authentication.getPrincipal();
        Fighter fighter =  fighterRepository.findById(securityUser.getId()).get();
        String status = licenceService.requestLicence(fighter, licence.getMedicalRecordNo(), licence.getPaymentConfirmationNo());

        System.out.println("status w wystap post" + status);
        if (!status.isEmpty()) {
            redirectAttributes.addFlashAttribute("LicenceError", status);
            return "redirect:/mojelicencje";
        }
        redirectAttributes.addFlashAttribute("LicenceSuccess", "Zgloszenie o licencje zostalo zalozone poprawnie!");


        return "redirect:/mojelicencje";

    }

}
