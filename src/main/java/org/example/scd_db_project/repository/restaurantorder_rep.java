package org.example.scd_db_project.repository;


import org.example.scd_db_project.model.RestaurantOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface restaurantorder_rep  extends JpaRepository<RestaurantOrder,Integer>{
}

