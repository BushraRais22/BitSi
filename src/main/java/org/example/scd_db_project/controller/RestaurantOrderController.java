package org.example.scd_db_project.controller;

import jakarta.servlet.http.HttpSession;
import org.example.scd_db_project.model.*;
import org.example.scd_db_project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class RestaurantOrderController {
    @Autowired
    private customer_rep customerRep;
    @Autowired
    private restaurantmenu_rep restaurantMenuRep;
    @Autowired
    private restaurantorder_rep restaurantOrderRep;
    @Autowired
    private orderitem_rep orderItemRep;
    @Autowired
    private restaurant_rep restaurantRep;
    @Autowired
    private menu_rep menuRep;

    @PostMapping("/cart/place-order")
    @ResponseBody
    public String placeOrder(@ModelAttribute("cart") Cart cart,
                             @RequestParam int restaurantId,
                             HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) {
            return "Customer not logged in! Please log in to place an order.";
        }

        // Log the received IDs
        System.out.println("Customer ID: " + customerId);
        System.out.println("Restaurant ID: " + restaurantId);


        // Fetch customer and restaurant
        Customer customer = customerRep.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Restaurant restaurant = restaurantRep.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with ID:" + restaurantId));

        // Create and save RestaurantOrder
        RestaurantOrder order = new RestaurantOrder();
        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setTotal_amount(cart.getTotalAmount());
        order.setStatus("Pending");
        order.setRo_date(new Date());
        restaurantOrderRep.save(order);

        // Save order items
        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setRestaurantOrder(order);

            Menu menu = menuRep.findById(cartItem.getMenuId())
                    .orElseThrow(() -> new IllegalArgumentException("Menu not found"));

            RestaurantMenuId restaurantMenuId = new RestaurantMenuId(restaurant, menu);
            RestaurantMenu restaurantMenu = restaurantMenuRep.findById(restaurantMenuId)
                    .orElseThrow(() -> new IllegalArgumentException("Restaurant menu item not found"));

            orderItem.setMenu(restaurantMenu.getId().getMenu());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItemRep.save(orderItem);
        }

        // Clear the cart
        cart.clear();

        return "Order placed successfully!";
    }
}
