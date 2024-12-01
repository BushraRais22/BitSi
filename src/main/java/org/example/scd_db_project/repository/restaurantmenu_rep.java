package org.example.scd_db_project.repository;

import org.example.scd_db_project.model.RestaurantMenu;
import org.example.scd_db_project.model.RestaurantMenuId;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface restaurantmenu_rep extends JpaRepository<RestaurantMenu, RestaurantMenuId> {
    @Query("SELECT rm FROM RestaurantMenu rm WHERE rm.id.restaurant.idd = :restaurantId")
    List<RestaurantMenu> findByRestaurantId(@Param("restaurantId") int restaurantId);
    Optional<RestaurantMenu> findById(RestaurantMenuId id);

}


