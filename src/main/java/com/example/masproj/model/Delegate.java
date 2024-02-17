package com.example.masproj.model;

import com.example.masproj.model.enums.Education;
import jakarta.persistence.*;

import java.util.Set;


@Entity
public class Delegate extends RegisteredMember{

    @Id
    @GeneratedValue
    private  long id;

    @Enumerated(EnumType.STRING)
    private Education educationLevel;


    @ManyToOne
    @JoinColumn(name = "UnionBranch_fk")
    UnionBranch unionBranch;


    @OneToOne(mappedBy = "delegate")
    private UnionBranch leading;

    @OneToMany(mappedBy = "asginedBy")
    private Set<Licence> licences;


    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public Education getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(Education educationLevel) {
        this.educationLevel = educationLevel;
    }

    public UnionBranch getUnionBranch() {
        return unionBranch;
    }

    public void setUnionBranch(UnionBranch unionBranch) {
        this.unionBranch = unionBranch;
    }

    public UnionBranch getLeading() {
        return leading;
    }

    public void setLeading(UnionBranch leading) {
        this.leading = leading;

    }
}
