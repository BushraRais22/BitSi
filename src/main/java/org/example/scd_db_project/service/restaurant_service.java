package org.example.scd_db_project.service;

import org.example.scd_db_project.model.Restaurant;
import org.example.scd_db_project.model.RestaurantOrder;
import org.example.scd_db_project.model.User;
import org.example.scd_db_project.repository.restaurant_rep;
import org.example.scd_db_project.repository.restaurantorder_rep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class restaurant_service {
    @Autowired
    private restaurant_rep r_rep;
    @Autowired
    private restaurantorder_rep restaurantorder_rep;

    public boolean CheckEmail(String email) {
        return r_rep.findByEmail(email).isPresent();
    }
    public Restaurant aadRestaurant(String email, int password, String name, String location, String phone,String restaurant_type){
        Restaurant r=new Restaurant();
        r.setR_name(name);
        r.setEmail(email);
        r.setPasswordd(password);
        r.setLocation(location);
        r.setR_phone(phone);
        r.setR_restaurant_type(restaurant_type);
        User u=new User();
        u.setUser_id(3);
        r.setUser(u);
        return r_rep.save(r);

    }
        public Restaurant validateLogin(String email, int password) {
            if(r_rep.findByEmail(email).isPresent()){
                Restaurant r=r_rep.findByEmail(email).get();
                if(r.getPasswordd() == password){
                    return r_rep.findByEmail(email).get();
                }
            }
           return null;
        }
    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> restaurants = r_rep.findAll();
        System.out.println("Restaurants from DB: " + restaurants); // Debug log
        return restaurants;
    }
    public List<RestaurantOrder> getOrdersByRestaurant(Integer restaurant_id) {
        return restaurantorder_rep.findByRestaurantIdd(restaurant_id);
    }

    public void updateOrderStatus(int orderId, String newStatus) {
        RestaurantOrder order = restaurantorder_rep.findById(orderId).orElseThrow(()-> new IllegalStateException("Order Not Found")    );
        order.setStatus(newStatus);
        restaurantorder_rep.save(order);
    }
}
