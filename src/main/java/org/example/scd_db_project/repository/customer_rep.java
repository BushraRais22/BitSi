package org.example.scd_db_project.repository;

import org.example.scd_db_project.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface customer_rep extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByEmail(String email);
}
