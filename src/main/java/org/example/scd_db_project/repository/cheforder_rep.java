package org.example.scd_db_project.repository;

import org.example.scd_db_project.model.ChefOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface cheforder_rep extends JpaRepository<ChefOrder,Integer> {
}
