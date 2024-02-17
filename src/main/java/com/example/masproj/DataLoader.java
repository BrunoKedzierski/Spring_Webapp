package com.example.masproj;

import com.example.masproj.Repositories.*;
import com.example.masproj.model.*;
import com.example.masproj.model.enums.Education;
import com.example.masproj.model.enums.FighterType;
import com.example.masproj.model.enums.LicenceStatus;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;


@Component
public class DataLoader {

    private final FighterRepository fighterRepository;
    private final ClubRepository clubRepository;
    private final DelegateRepository delegateService;
    private final UnionBranchRepository unionBranchService ;
    private final ContestRepository contestRepository;
    private final EntryRepository entryRepository;
    private final LicenceRepository licenceRepository;

    @Autowired
    public DataLoader(FighterRepository fighterRepository, ClubRepository clubRepository, DelegateRepository delegateService, UnionBranchRepository unionBranchService, ContestRepository contestRepository, EntryRepository entryRepository, LicenceRepository licenceRepository) {
        this.fighterRepository = fighterRepository;
        this.clubRepository = clubRepository;
        this.delegateService = delegateService;
        this.unionBranchService = unionBranchService;
        this.contestRepository = contestRepository;
        this.entryRepository = entryRepository;
        this.licenceRepository = licenceRepository;
    }

    @PostConstruct
    public void loadData() {
//        /** Initialize Clubs */
//        Club club1 = new Club();
//        club1.setAdress("ul. panska 12, Warszawa");
//        club1.setName("Fighter Warzawa");
//
//        Club club2 = new Club();
//        club2.setAdress("ul. poznanska 2, Poznan");
//        club2.setName("KO Poznan");
//
//        Club club3 = new Club();
//        club3.setAdress("ul. prosta 19, Katowice");
//        club3.setName("KS Katowiczanka");
//
//        Club club4 = new Club();
//        club4.setAdress("ul. Dluga, Kielce");
//        club4.setName("Viva Kliece");
//        clubRepository.saveAll(Arrays.asList(club1,club2,club3, club4));
//
//        /** Initialize Fighers */
//
//        Fighter fighter1 = new Fighter();
//        fighter1.setName("Alojzy");
//        fighter1.setSurname("Misiak");
//        fighter1.setPesel("970123100");
//        fighter1.setEmail("alojzym@pzkb.pl");
//        fighter1.setAdult(true);
//        fighter1.addFighterType(FighterType.ZAWODNIK);
//        fighter1.setClub(club1);
//        fighter1.setBirthday(LocalDate.now().minusYears(22));
//        fighter1.setPassword("testowe");
//
//        Fighter fighter2 = new Fighter();
//        fighter2.setName("Marian");
//        fighter2.setSurname("Kruk");
//        fighter2.setEmail("mkruk@pzkb.pl");
//        fighter2.setPassword("testowe");
//        fighter2.setClub(club1);
//        fighter2.setHasCollege(true);
//        fighter2.setIntLicense(false);
//        fighter2.addFighterType(FighterType.TRENER);
//        fighter2.setBirthday(LocalDate.now().minusYears(42));
//
//        Fighter fighter3 = new Fighter();
//        fighter3.setName("Adam");
//        fighter3.setSurname("Krzak");
//        fighter3.setPesel("970135200");
//        fighter3.setEmail("krzak@pzkb.pl");
//        fighter3.setAdult(true);
//        fighter3.addFighterType(FighterType.ZAWODNIK);
//        fighter3.setClub(club2);
//        fighter3.setBirthday(LocalDate.now().minusYears(19));
//        fighter3.setPassword("testowe");
//
//        Fighter fighterTest = new Fighter();
//        fighterTest.setName("Test");
//        fighterTest.setSurname("Testowy");
//        fighterTest.setPesel("9701354200");
//        fighterTest.setEmail("test@pzkb.pl");
//        fighterTest.setAdult(true);
//        fighterTest.addFighterType(FighterType.ZAWODNIK);
//        fighterTest.setClub(club2);
//        fighterTest.setBirthday(LocalDate.now().minusYears(33));
//        fighterTest.setPassword("testowe");
//
//        fighterRepository.saveAll(Arrays.asList(fighter1,fighter3,fighter2,fighterTest));
//
//
//        /** Initialize Delegates */
//        Delegate delegate1 = new Delegate();
//        delegate1.setName("Adrian");
//        delegate1.setSurname("Kolanko");
//        delegate1.setEmail("ew ");
//        delegate1.setPassword("testowe");
//        delegate1.setEducationLevel(Education.COLLEGE);
//        delegate1.setBirthday(LocalDate.now().minusYears(66));
//        delegateService.save(delegate1);
//
//
//        /** Initialize a conest */
//        Contest cn1 = new Contest();
//        cn1.setName("MP Low Kick");
//        cn1.setHostingClub(club3);
//        cn1.setAddress(club3.getAdress());
//        cn1.setBeginOn(LocalDate.parse("2023-12-01"));
//        cn1.setEndOn(LocalDate.parse("2023-12-04"));
//
//
//
//        Contest cn2 = new Contest();
//        cn2.setName("MP K1");
//        cn2.setHostingClub(club2);
//        cn2.setAddress(club2.getAdress());
//        cn2.setBeginOn(LocalDate.parse("2023-11-01"));
//        cn2.setEndOn(LocalDate.parse("2023-11-05"));
//
//
//        Contest cn3 = new Contest();
//        cn3.setName("Puchar Polski");
//        cn3.setHostingClub(club1);
//        cn3.setAddress(club1.getAdress());
//        cn3.setBeginOn(LocalDate.parse("2024-03-01"));
//        cn3.setEndOn(LocalDate.parse("2024-03-04"));
//
//        Contest cn4 = new Contest();
//        cn4.setName("Grand Prix Polski");
//        cn4.setHostingClub(club1);
//        cn4.setAddress(club1.getAdress());
//        cn4.setBeginOn(LocalDate.parse("2024-04-02"));
//        cn4.setEndOn(LocalDate.parse("2024-04-06"));
//
//        contestRepository.saveAll(Arrays.asList(cn1,cn2,cn3,cn4));
//
//
//        /** Add licences **/
//        Licence licence1 = new Licence();
//        licence1.setFighter(fighter1);
//        licence1.setPaymentConfirmationNo("123123123");
//        licence1.setMedicalRecordNo("5326236");
//        licence1.setStatus(LicenceStatus.ZATWIERDZONA);
//        licence1.setAsginedBy(delegate1);
//        licence1.setBeginsOn(LocalDate.parse("2023-12-01"));
//        licence1.setEndsOn(LocalDate.parse("2024-01-01"));
//
//
//        Licence licence2 = new Licence();
//        licence2.setFighter(fighter2);
//        licence2.setPaymentConfirmationNo("123123123");
//        licence2.setMedicalRecordNo("5326236");
//        licence2.setStatus(LicenceStatus.ZATWIERDZONA);
//        licence2.setAsginedBy(delegate1);
//        licence2.setBeginsOn(LocalDate.parse("2023-10-01"));
//        licence2.setEndsOn(LocalDate.parse("2024-01-01"));
//
//
//        Licence licence3 = new Licence();
//        licence3.setFighter(fighter3);
//        licence3.setPaymentConfirmationNo("123123123");
//        licence3.setMedicalRecordNo("5326236");
//        licence3.setStatus(LicenceStatus.ZATWIERDZONA);
//        licence3.setAsginedBy(delegate1);
//        licence3.setBeginsOn(LocalDate.parse("2023-10-01"));
//        licence3.setEndsOn(LocalDate.parse("2023-11-20"));
//
//        licenceRepository.saveAll(Arrays.asList(licence1,licence2, licence3));
//
//        /** Add Entries **/
//        Entry entry1 = new Entry();
//        entry1.setFighter(fighter1);
//        entry1.setContest(cn1);
//        entry1.setSubmittedOn(LocalDate.parse("2023-11-01"));
//
//        Entry entry2 = new Entry();
//        entry2.setFighter(fighter2);
//        entry2.setContest(cn1);
//        entry2.setSubmittedOn(LocalDate.parse("2023-11-12"));
//
//        Entry entry3 = new Entry();
//        entry3.setFighter(fighter2);
//        entry3.setContest(cn2);
//        entry3.setSubmittedOn(LocalDate.parse("2023-10-07"));
//
//
//        Entry entry4 = new Entry();
//        entry4.setFighter(fighter3);
//        entry4.setContest(cn2);
//        entry4.setSubmittedOn(LocalDate.parse("2023-10-01"));
//
//        entryRepository.saveAll(Arrays.asList(entry1, entry2, entry3, entry4));








    }
}