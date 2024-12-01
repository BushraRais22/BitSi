package org.example.scd_db_project.repository;

import org.example.scd_db_project.model.Chef;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface chef_rep extends JpaRepository<Chef, Integer> {
    Optional<Chef> findByEmail(String Email);
}
