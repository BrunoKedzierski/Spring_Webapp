package com.example.masproj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Club {

    @Id
    @GeneratedValue
    private long id;

    @NotEmpty(message = "Nazwa klubu nie moze byc pusta!")
    private String name;
    @NotEmpty(message = "Adres klubu nie moze byc pusty!")
    private String adress;
    private String mobile;
    private String owner;

    @OneToMany(mappedBy = "hostingClub")
    private Set<Contest> contests = new HashSet<>();

    @OneToMany(mappedBy = "club")
    private Set<Fighter> fighters = new HashSet<>();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Set<Contest> getContests() {
        return contests;
    }

    public void setContests(Set<Contest> contests) {
        this.contests = contests;
    }

    public Set<Fighter> getFighters() {
        return fighters;
    }

    public void setFighters(Set<Fighter> fighters) {
        this.fighters = fighters;
    }
}
