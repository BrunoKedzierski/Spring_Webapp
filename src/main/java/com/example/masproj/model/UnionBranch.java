package com.example.masproj.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class UnionBranch {

    @Id
    @GeneratedValue
    private  long id;

    private String name;
    private String address;

    @OneToMany(mappedBy = "unionBranch")
    private Set<Delegate> delegates = new HashSet<>();


    @OneToOne
    @JoinColumn(name = "leader_id")
    private Delegate delegate;


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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Delegate> getDelegates() {
        return delegates;
    }

    public void setDelegates(Set<Delegate> delegates) {
        this.delegates = delegates;
    }

    public Delegate getDelegate() {
        return delegate;
    }

    public void setDelegate(Delegate delegate) {
        this.delegate = delegate;
    }
}
