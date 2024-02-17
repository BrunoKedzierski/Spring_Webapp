package com.example.masproj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class AgeCategory {

    @Id
    @GeneratedValue
    private long id;

    private String name;
    private int startingFrom;
    private int endValue;
}
