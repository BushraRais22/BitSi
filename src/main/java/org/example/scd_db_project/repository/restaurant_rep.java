package org.example.scd_db_project.repository;

import org.example.scd_db_project.model.Chef;
import org.example.scd_db_project.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface restaurant_rep extends JpaRepository<Restaurant, Integer> {
    Optional<Restaurant> findByEmail(String email);
    @Query("SELECT r FROM Restaurant r")
    List<Restaurant> findAllRestaurants();
}


