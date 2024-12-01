package org.example.scd_db_project.repository;

import org.example.scd_db_project.model.RestaurantRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface restaurantrating_rep extends JpaRepository<RestaurantRating,Integer> {
}
