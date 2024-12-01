package org.example.scd_db_project.repository;

import org.example.scd_db_project.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface orderitem_rep  extends JpaRepository<OrderItem,Integer> {
}
