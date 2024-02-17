package com.example.masproj.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Contest {

    @Id
    @GeneratedValue
    private long id;

    @NotEmpty(message = "Nazwa zawodow nie moze byc pusta!")
    private String name;
    @NotNull(message = "Nalezy podac date poczatku zawodow!")
    private LocalDate beginOn;
    @NotNull(message = "Nalezy podac date konca zawodow!")
    private LocalDate endOn;
    @NotNull
    private String address;


    @OneToMany(mappedBy = "contest")
    private Set<Entry> entries = new HashSet<>();

    @NotNull
    @ManyToOne
    @JoinColumn(name = "club_fk")
    private Club hostingClub;


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

    public LocalDate getBeginOn() {
        return beginOn;
    }

    public void setBeginOn(LocalDate beginOn) {
        this.beginOn = beginOn;
    }

    public LocalDate getEndOn() {
        return endOn;
    }

    public void setEndOn(LocalDate endOn) {
        this.endOn = endOn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Entry> getEntries() {
        return entries;
    }

    public void setEntries(Set<Entry> entries) {
        this.entries = entries;
    }

    public Club getHostingClub() {
        return hostingClub;
    }

    public void setHostingClub(Club hostingClub) {
        this.hostingClub = hostingClub;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Contest{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", beginOn=").append(beginOn);
        sb.append(", endOn=").append(endOn);
        sb.append(", address='").append(address).append('\'');
        sb.append(", entries=").append(entries);
        sb.append(", hostingClub=").append(hostingClub);
        sb.append('}');
        return sb.toString();
    }
}
