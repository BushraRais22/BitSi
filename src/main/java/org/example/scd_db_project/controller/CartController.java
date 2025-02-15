package org.example.scd_db_project.controller;

import org.example.scd_db_project.model.*;
import org.example.scd_db_project.repository.menu_rep;
import org.example.scd_db_project.repository.restaurant_rep;
import org.example.scd_db_project.repository.restaurantmenu_rep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

    @Autowired
    private restaurantmenu_rep restaurantmenu_repository;

    @Autowired
    private restaurant_rep restaurant_rep;

    @Autowired
    private menu_rep menu_rep;

    @PostMapping("/cart/add")
    @ResponseBody
    public Cart addToCart(HttpSession session,
                          @RequestParam int menuId,
                          @RequestParam int restaurantId,
                          @RequestParam int quantity) {

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        Restaurant restaurant = restaurant_rep.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid restaurant ID: " + restaurantId));

        Menu menu = menu_rep.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid menu ID: " + menuId));

        RestaurantMenuId restaurantMenuId = new RestaurantMenuId(restaurant, menu);

        RestaurantMenu rm = restaurantmenu_repository.findById(restaurantMenuId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid menu item: " + menuId));

        cart.addItem(new CartItem(menuId, rm.getId().getMenu().getName(), rm.getRm_price(), quantity));

        return cart;
    }

    @GetMapping("/cart")
    @ResponseBody
    public Cart viewCart(HttpSession session) {

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        return cart;
    }
    @PostMapping("/cart/clear")
    @ResponseBody
    public void clearCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            cart.clear();
        }
        session.setAttribute("cart", null);
    }

}
