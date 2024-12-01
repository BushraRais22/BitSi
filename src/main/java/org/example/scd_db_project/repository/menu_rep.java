package org.example.scd_db_project.repository;

import org.example.scd_db_project.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface menu_rep extends JpaRepository<Menu, Integer> {
}
