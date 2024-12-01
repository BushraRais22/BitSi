package org.example.scd_db_project.controller;

import org.example.scd_db_project.model.Restaurant;
import org.example.scd_db_project.model.RestaurantMenu;
import org.example.scd_db_project.service.restaurant_service;
import org.example.scd_db_project.service.restaurantmenu_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/restaurant_ctrl")
public class restaurant_ctrl {
    @Autowired
    private restaurant_service r_service;
    @Autowired
    private restaurantmenu_service rm_service;
    @PostMapping("/signup")
    public String signup(
            @RequestParam("email") String email,
            @RequestParam("password")int password,
            @RequestParam("r_name") String name,
            @RequestParam("restaurant_type")String type,
            @RequestParam("r_phone") String phone,
            @RequestParam("r_location")String location, Model model
            ){
        if(r_service.CheckEmail(email)){
            model.addAttribute("message", "Email Already Exists");
            return "business_signup";
        }
        r_service.aadRestaurant(email, password, name, type, phone, location);
        model.addAttribute("message", "Signup Successful");
        return "restaurant_homepage";
    }
    @PostMapping("/login")
    public String login(@RequestParam("email")String email, @RequestParam("password") int password, Model model){
        Restaurant r= r_service.validateLogin(email, password);
        if(r!=null){
            model.addAttribute("message", "Login Successful");
            return "restaurant_homepage";
        }else{
            model.addAttribute("message", "Login Failed");
            return "restaurant_login";
        }
    }
    @GetMapping("/restaurants")
    public String getAllRestaurants(Model model) {
        List<Restaurant> restaurants = r_service.getAllRestaurants();
        model.addAttribute("restaurants", restaurants);
        return "home";
    }

    @GetMapping("/restaurants/{id}/menu")
    public String getMenuByRestaurant(@PathVariable int id, Model model) {
        List<RestaurantMenu> menuItems = rm_service.findByRestaurantId(id);
        menuItems.forEach(item -> {
            System.out.println("Menu Item: " + item);
            System.out.println("Menu Name: " + (item.getId().getMenu() != null ? item.getId().getMenu().getName() : "No Menu Object"));
        });
        model.addAttribute("menuItems", menuItems);
        model.addAttribute("restaurantId", id);
        return "restaurant_menu";
    }


}
