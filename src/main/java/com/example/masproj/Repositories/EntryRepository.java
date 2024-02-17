package com.example.masproj.Repositories;

import com.example.masproj.model.Contest;
import com.example.masproj.model.Entry;
import com.example.masproj.model.Fighter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EntryRepository extends JpaRepository<Entry, Long> {

    List<Entry> findAllByContest(Contest contest);

    Optional<Entry> findByFighterAndAndContest(Fighter fighter, Contest contest);
}
