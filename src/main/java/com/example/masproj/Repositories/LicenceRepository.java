package com.example.masproj.Repositories;

import com.example.masproj.model.Fighter;
import com.example.masproj.model.Licence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LicenceRepository extends JpaRepository<Licence, Long> {

    Optional<List<Licence>> findAllByFighter(Fighter f);
}
