package org.example.scd_db_project.repository;

import org.example.scd_db_project.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface orderitem_rep extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findByRestaurantOrderId(int ro_id);
}
