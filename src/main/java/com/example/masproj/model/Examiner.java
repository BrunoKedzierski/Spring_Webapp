package com.example.masproj.model;

import com.example.masproj.model.enums.Rank;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
public class Examiner extends RegisteredMember{
    @NotNull
    private boolean intLicense;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Rank maxRank;


}
