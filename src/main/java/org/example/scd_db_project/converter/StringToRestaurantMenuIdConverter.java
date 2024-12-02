package org.example.scd_db_project.converter;

import org.example.scd_db_project.repository.menu_rep;
import org.example.scd_db_project.repository.restaurant_rep;
import org.example.scd_db_project.service.menu_service;
import org.example.scd_db_project.service.restaurant_service;
import org.springframework.core.convert.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.example.scd_db_project.model.RestaurantMenuId;
import org.example.scd_db_project.model.Restaurant;
import org.example.scd_db_project.model.Menu;

@Component
public class StringToRestaurantMenuIdConverter implements Converter<String, RestaurantMenuId> {
    @Autowired
    private restaurant_rep restaurantRepository;
    @Autowired
    private menu_rep menuRepository ;
    private final restaurant_service restaurantService;
    private final menu_service menuService;
    public StringToRestaurantMenuIdConverter(restaurant_service restaurantService, menu_service menuService) {
        this.restaurantService = restaurantService;
        this.menuService = menuService;
    }

    public RestaurantMenuId convert(String source) {
        if(source == null || source.isEmpty()) {
            return null;
        }
        String[] parts = source.split("-");
        if(parts.length < 2) {
            return null;
        }
        int restaurantId = Integer.parseInt(parts[0]);
        int menuId = Integer.parseInt(parts[1]);

        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new IllegalArgumentException("Invalid restaurant ID"));
        Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new IllegalArgumentException("Invalid menu ID"));

        return new RestaurantMenuId(restaurant, menu);
    }
}
