package org.example.scd_db_project.repository;

import org.example.scd_db_project.model.ChefRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  chefrating_rep extends JpaRepository<ChefRating,Integer> {
}
