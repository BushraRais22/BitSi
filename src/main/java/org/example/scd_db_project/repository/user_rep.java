package org.example.scd_db_project.repository;

import org.example.scd_db_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface user_rep extends JpaRepository<User, Integer> {
}
