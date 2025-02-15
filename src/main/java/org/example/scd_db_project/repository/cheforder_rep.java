package org.example.scd_db_project.repository;

import org.example.scd_db_project.model.ChefOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface cheforder_rep extends JpaRepository<ChefOrder,Integer> {

    @Query("SELECT co FROM ChefOrder co WHERE co.chef.id = :chefId")
    List<ChefOrder> findOrdersByChefId(@Param("chefId") Integer chefId);
}
