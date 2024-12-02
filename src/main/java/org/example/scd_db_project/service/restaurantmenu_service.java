package org.example.scd_db_project.service;

import org.example.scd_db_project.model.RestaurantMenu;
import org.example.scd_db_project.model.RestaurantMenuId;
import org.example.scd_db_project.repository.restaurant_rep;
import org.example.scd_db_project.repository.restaurantmenu_rep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class restaurantmenu_service {
    @Autowired
    private restaurantmenu_rep rm_rep;

    //for order management
    public List<RestaurantMenu> findByRestaurantId(int restaurantId) {
        return rm_rep.findByRestaurantId(restaurantId);
    }

    //for menus management
    public List<RestaurantMenu> getMenusByRestaurant(Integer restaurantId) {
        return rm_rep.findByRestaurantId(restaurantId);
    }

    public void updateMenuDetails(RestaurantMenuId menuId, double price, boolean availability) {
        RestaurantMenu menu = rm_rep.findById(menuId).orElseThrow(() -> new IllegalArgumentException("Menu not found"));
        menu.setRm_price(price);
        menu.setRm_availability(availability);
        rm_rep.save(menu);
    }

}
