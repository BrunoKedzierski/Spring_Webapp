package com.example.masproj.Repositories;

import com.example.masproj.model.Fighter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FighterRepository extends JpaRepository<Fighter,Long> {
    Optional<Fighter> findByEmail(String email);


}
