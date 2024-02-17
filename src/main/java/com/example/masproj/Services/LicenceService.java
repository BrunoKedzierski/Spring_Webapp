package com.example.masproj.Services;

import com.example.masproj.Repositories.FighterRepository;
import com.example.masproj.Repositories.LicenceRepository;
import com.example.masproj.model.Delegate;
import com.example.masproj.model.Fighter;
import com.example.masproj.model.Licence;
import com.example.masproj.model.enums.LicenceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LicenceService {


    LicenceRepository licenceRepository;
    FighterRepository fighterRepository;

    @Autowired
    public LicenceService(LicenceRepository licenceRepository, FighterRepository fighterRepository) {
        this.licenceRepository = licenceRepository;
        this.fighterRepository = fighterRepository;
    }

    public boolean hasALicence(Fighter fighter){
        Optional<List<Licence>> licences = licenceRepository.findAllByFighter(fighter);
        if(licences.isPresent())
            return true;

        return false;

    }


    public boolean hasValidLicenceForPeriod(Fighter fighter, LocalDate start, LocalDate end){

        Optional<List<Licence>> licences = licenceRepository.findAllByFighter(fighter);

        if (!licences.isPresent())
            return false;

        for (Licence l : licences.get()) {

            if(l.getStatus().equals(LicenceStatus.ZATWIERDZONA)) {
                LocalDate licenceBeginsOn = l.getBeginsOn();
                LocalDate licenceEndsOn = l.getEndsOn();
                if ((licenceBeginsOn.equals(start) || licenceBeginsOn.isBefore(start)) && (licenceEndsOn.equals(end) || licenceEndsOn.isAfter(end)))
                    return true;
            }

        }

        return false;
    }


    public Boolean hasPending(Fighter fighter){
        Optional<List<Licence>> licences = licenceRepository.findAllByFighter(fighter);
        if (!licences.isPresent())
            return false;

        for (Licence l: licences.get()) {
            if(l.getStatus().equals(LicenceStatus.OCZEKUJACA))
                return true;

        }

        return false;
    }


    public Optional<List<Licence>> getLicencesForFigther(Fighter fighter){
        return licenceRepository.findAllByFighter(fighter);
    }


    public List<Licence> getAllLicences(){
        return licenceRepository.findAll();
    }


    public Optional<Licence> getLicenceById(long id){

        return licenceRepository.findById(id);

    }



    public String requestLicence(Fighter fighter, String medical, String payment){
        if(hasPending(fighter))
            return "Masz oczekujace licencje, poczekaj az zostana rozpatrzone, zanim wystapisz o kolejna!";

        Licence licence = new Licence();
        licence.setMedicalRecordNo(medical);
        licence.setPaymentConfirmationNo(payment);
        licence.setFighter(fighter);
        licence.setStatus(LicenceStatus.OCZEKUJACA);

        licenceRepository.save(licence);
        return "";

    }


    public String assignLicence(Licence licence){
        if(licence.getStatus().equals(LicenceStatus.ZATWIERDZONA)){
            if(licence.getBeginsOn() == null || licence.getEndsOn() == null)
                return "Aby zatwierdzic licencje, nalezy podac date poczatku i konca waznosci licencji!";
            if(!licence.getBeginsOn().isBefore(licence.getEndsOn()))
                return "Licencja musi konczyc sie pozniej niz jej poczatek";

        }




        licenceRepository.save(licence);
        return "";
    }




}
