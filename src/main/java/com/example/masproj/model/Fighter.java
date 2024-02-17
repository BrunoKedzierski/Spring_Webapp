package com.example.masproj.model;

import com.example.masproj.model.enums.CoachingClass;
import com.example.masproj.model.enums.FighterType;
import com.example.masproj.model.enums.SportClass;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Fighter extends RegisteredMember{

    @NotNull
    @ElementCollection
    @CollectionTable(name = "fighter_type", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private Set<FighterType> fighterType = EnumSet.noneOf(FighterType.class);

    @Enumerated(EnumType.STRING)
    private SportClass sportClass;

    private String  pesel;
    private boolean isAdult;
    private boolean hasCollege;
    private boolean intLicense;

    @Enumerated(EnumType.STRING)
    private CoachingClass coachingClass;

    @OneToMany(mappedBy = "fighter")
    private Set<Entry> entries = new HashSet<>();

    @NotNull
    @ManyToOne
    @JoinColumn(name = "club_fk")
    private Club club;


    @OneToMany(mappedBy = "fighter")
    private Set<Licence> licences;

    public Set<FighterType> getFighterType() {
        return fighterType;
    }

    public void setFighterType(EnumSet<FighterType> fighterType) {
        this.fighterType = fighterType;
    }


    public void addFighterType(FighterType fighterType) {
        this.fighterType.add(fighterType);
    }

    public SportClass getSportClass() {
        return sportClass;
    }

    public void setSportClass(SportClass sportClass) {
        this.sportClass = sportClass;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public boolean isHasCollege() {
        return hasCollege;
    }

    public void setHasCollege(boolean hasCollege) {
        this.hasCollege = hasCollege;
    }

    public boolean isIntLicense() {
        return intLicense;
    }

    public void setIntLicense(boolean intLicense) {
        this.intLicense = intLicense;
    }

    public CoachingClass getCoachingClass() {
        return coachingClass;
    }

    public void setCoachingClass(CoachingClass coachingClass) {
        this.coachingClass = coachingClass;
    }

    public Set<Entry> getEntries() {
        return entries;
    }

    public void setEntries(Set<Entry> entries) {
        this.entries = entries;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Set<Licence> getLicences() {
        return licences;
    }

    public void setLicences(Set<Licence> licences) {
        this.licences = licences;
    }
}
