package com.example.masproj.Repositories;

import com.example.masproj.model.Contest;
import com.example.masproj.model.Fighter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContestRepository extends JpaRepository<Contest, Long> {


    Optional<List<Contest>> findAllByEntriesFighter(Fighter fighter);
}
