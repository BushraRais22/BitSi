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

        // Retrieve or initialize the cart from the session
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        // Create RestaurantMenuId using the restaurant and menu IDs
        Restaurant restaurant = restaurant_rep.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid restaurant ID: " + restaurantId));

        Menu menu = menu_rep.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid menu ID: " + menuId));

        RestaurantMenuId restaurantMenuId = new RestaurantMenuId(restaurant, menu);

        // Fetch the menu item from the database
        RestaurantMenu rm = restaurantmenu_repository.findById(restaurantMenuId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid menu item: " + menuId));

        // Add the item to the cart
        cart.addItem(new CartItem(menuId, rm.getId().getMenu().getName(), rm.getRm_price(), quantity));

        return cart; // Return the updated cart as JSON
    }

    @GetMapping("/cart")
    @ResponseBody
    public Cart viewCart(HttpSession session) {
        // Retrieve the cart from the session
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        return cart; // Return the cart as JSON
    }
    @PostMapping("/cart/clear")
    @ResponseBody
    public void clearCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            cart.clear(); // Clear all items in the cart
        }
        session.setAttribute("cart", null); // Optionally remove the cart from the session
    }

}
