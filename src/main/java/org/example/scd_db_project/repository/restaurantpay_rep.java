package org.example.scd_db_project.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.scd_db_project.model.RestaurantPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface restaurantpay_rep extends JpaRepository<RestaurantPayment,Integer> {
}
