package com.example.masproj.model;

import com.example.masproj.model.enums.Outcome;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"fighter_a_id", "fighter_b_id"}))
public class Fight {

    @Id
    @GeneratedValue
    private  long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Outcome outcome;
    private int ringNo;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "fighter_a_id")
    private Entry fighterA;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fighter_b_id")
    private Entry fighterB;

}
