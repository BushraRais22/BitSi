package org.example.scd_db_project.service;

import org.example.scd_db_project.model.RestaurantMenu;
import org.example.scd_db_project.repository.restaurantmenu_rep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class restaurantmenu_service {
    @Autowired
    private restaurantmenu_rep rm_rep;

    public List<RestaurantMenu> findByRestaurantId(int restaurantId) {
        return rm_rep.findByRestaurantId(restaurantId);
    }
}
