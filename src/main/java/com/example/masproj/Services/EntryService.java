package com.example.masproj.Services;

import com.example.masproj.Repositories.ContestRepository;
import com.example.masproj.Repositories.EntryRepository;
import com.example.masproj.model.Contest;
import com.example.masproj.model.Entry;
import com.example.masproj.model.Fighter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EntryService {


    private EntryRepository entryRepository;
    private LicenceService licenceService;
    private ContestService contestService;
    private ContestRepository contestRepository;


    public EntryService(EntryRepository entryRepository, LicenceService licenceService, ContestService contestService,ContestRepository contestRepository) {
        this.entryRepository = entryRepository;
        this.licenceService = licenceService;
        this.contestService = contestService;
        this.contestRepository = contestRepository;
    }

    public String unregister(Fighter fighter, Long contestid){
        Optional<Contest> contest =  contestRepository.findById(contestid);
        if(!contest.isPresent())
            return "Nie ma takich zawodow!";
        Optional<Entry> entry = entryRepository.findByFighterAndAndContest(fighter, contest.get());
        if(!entry.isPresent())
            return "Nie jestes zapisany na te zawody!";
        if(contestService.hasClosed(contest.get()))
            return "Rejestracja/Derejestracja na te zawody jest zamknieta";
        entryRepository.delete(entry.get());
        return "";
    }



    public String enterContest(Fighter fighter, Contest contest){
        String returnMessage = "";

        returnMessage = closedForEntry(contest);
        if(!returnMessage.isEmpty())
            return returnMessage;


        returnMessage = checkForDuplicateEntry(fighter,contest);
        if(!returnMessage.isEmpty())
            return returnMessage;

        returnMessage = checkValidLicense(fighter,contest);
        if(!returnMessage.isEmpty())
            return returnMessage;


        Entry entry = registerAnEntry(fighter,contest);
        entryRepository.save(entry);
        return  returnMessage;


    }

    public Entry registerAnEntry(Fighter fighter, Contest contest){
        Entry entry = new Entry();
        entry.setSubmittedOn(LocalDate.now());
        entry.setFighter(fighter);
        entry.setContest(contest);
        return entry;
    }

    private String checkForDuplicateEntry(Fighter fighter, Contest contest){
        String returnMessage = "";

        Optional<Entry> existingEntry = entryRepository.findByFighterAndAndContest(fighter,contest);

        if(existingEntry.isPresent())
            returnMessage = "Jestes juz zapisany na te zawody !";
        return returnMessage;

    }

    private String closedForEntry(Contest contest){
        String returnMessage = "";
        if(contestService.hasClosed(contest))
        {
            returnMessage = "Ten turniej nie przyjmuje już zgłoszeń!";
        }

        return returnMessage;

    }


    private String checkValidLicense(Fighter fighter, Contest contest) {
        String returnMessage = "Nie posiadasz licencji, waznej na czas trwania zawodow!";

        LocalDate start = contest.getBeginOn();
        LocalDate end = contest.getEndOn();

        if(licenceService.hasValidLicenceForPeriod(fighter,start,end))
            return "";


        return returnMessage;
    }






}
