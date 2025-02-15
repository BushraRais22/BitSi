package org.example.scd_db_project.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Map;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

import org.example.scd_db_project.model.*;
import org.example.scd_db_project.repository.*;


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
    public ResponseEntity<Map<String, Object>> placeOrder(HttpSession session, @RequestParam("restaurantId") int restaurantId, @RequestParam("specifications") String specifications) {
        System.out.println("Received restaurantId: " + restaurantId);
        System.out.println("Received specifications: " + specifications);
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Customer not logged in! Please log in to place an order."));
        }
        System.out.println("Customer ID: " + customerId);
        System.out.println("Restaurant ID: " + restaurantId);


        Customer customer = customerRep.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Restaurant restaurant = restaurantRep.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with ID:" + restaurantId));

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getItems().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Cart is empty!"));
        }
        RestaurantOrder order = new RestaurantOrder();
        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setTotal_amount(cart.getTotalAmount());
        order.setStatus("Pending");
        order.setRo_date(new Date());
        order.setSpecifications(specifications);
        restaurantOrderRep.save(order);


        cart.getItems().forEach(item -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setRestaurantOrder(order);
            Menu menu = menuRep.findById(item.getMenuId())
                    .orElseThrow(() -> new RuntimeException("Menu not found"));
            RestaurantMenuId restaurantMenuId = new RestaurantMenuId(restaurant, menu);
            RestaurantMenu restaurantMenu = restaurantMenuRep.findById(restaurantMenuId)
                    .orElseThrow(() -> new RuntimeException("Restaurant menu item not found"));
            orderItem.setMenu(restaurantMenu.getId().getMenu());
            orderItem.setQuantity(item.getQuantity());
            orderItemRep.save(orderItem);
        });

        cart.clear();
        session.setAttribute("cart", cart);
        return ResponseEntity.ok(Map.of("message", "Order placed successfully!", "orderId", order.getId()));
    }
}
