package com.example.masproj.Services;

import com.example.masproj.Repositories.ContestRepository;
import com.example.masproj.Repositories.FighterRepository;
import com.example.masproj.Repositories.LicenceRepository;
import com.example.masproj.model.Contest;
import com.example.masproj.model.Fighter;
import com.example.masproj.model.Licence;
import com.example.masproj.model.enums.LicenceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContestService {


    ContestRepository contestRepository;


    @Autowired
    public ContestService(ContestRepository contestRepository) {

    }



    public boolean hasClosed(Contest contest){
        return contest.getBeginOn().isBefore(LocalDate.now());

    }





    private String createContest(Contest contest){
        contestRepository.save(contest);
        return "";
    }




}
