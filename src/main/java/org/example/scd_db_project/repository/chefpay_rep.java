package org.example.scd_db_project.repository;

import org.example.scd_db_project.model.ChefPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface chefpay_rep extends JpaRepository<ChefPayment,Integer> {
}
