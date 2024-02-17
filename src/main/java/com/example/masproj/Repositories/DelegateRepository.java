package com.example.masproj.Repositories;

import com.example.masproj.model.Delegate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DelegateRepository extends JpaRepository<Delegate, Long> {

    Optional<Delegate> findByEmail(String email);


}
