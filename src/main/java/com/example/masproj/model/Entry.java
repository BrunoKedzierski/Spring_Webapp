package com.example.masproj.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"fighter_fk", "contest_fk"}))
public class Entry {

    @Id
    @GeneratedValue
    private long entryId;

    @NotNull
    private LocalDate submittedOn;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "fighter_fk")
    private Fighter fighter;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "contest_fk")
    private Contest contest;

    @OneToMany(mappedBy = "fighterA")
    private List<Fight> fightsAsFighterA = new ArrayList<>();

    @OneToMany(mappedBy = "fighterB")
    private List<Fight> fightsAsFighterB = new ArrayList<>();

    public long getEntryId() {
        return entryId;
    }

    public void setEntryId(long entryId) {
        this.entryId = entryId;
    }

    public LocalDate getSubmittedOn() {
        return submittedOn;
    }

    public void setSubmittedOn(LocalDate submittedOn) {
        this.submittedOn = submittedOn;
    }

    public Fighter getFighter() {
        return fighter;
    }

    public void setFighter(Fighter fighter) {
        this.fighter = fighter;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public List<Fight> getFightsAsFighterA() {
        return fightsAsFighterA;
    }

    public void setFightsAsFighterA(List<Fight> fightsAsFighterA) {
        this.fightsAsFighterA = fightsAsFighterA;
    }

    public List<Fight> getFightsAsFighterB() {
        return fightsAsFighterB;
    }

    public void setFightsAsFighterB(List<Fight> fightsAsFighterB) {
        this.fightsAsFighterB = fightsAsFighterB;
    }
}
