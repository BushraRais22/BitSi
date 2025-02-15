package org.example.scd_db_project.controller;

import org.example.scd_db_project.model.OrderItem;
import org.example.scd_db_project.model.RestaurantMenu;
import org.example.scd_db_project.model.RestaurantMenuId;
import org.example.scd_db_project.repository.orderitem_rep;
import org.example.scd_db_project.repository.restaurantmenu_rep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/payment")
public class payment_ctrl {

    @Autowired
    private orderitem_rep orderItemRepository;

    @Autowired
    private restaurantmenu_rep restaurantMenuRepository;
    @PostMapping("/process")
    @ResponseBody
    public String processPayment(@RequestParam int orderId,
                                 @RequestParam double totalAmount,
                                 @RequestParam String paymentMethod){
        return "Payment processed successfully for Order ID: " + orderId + " with " + paymentMethod;

    }
    @GetMapping("/details/{orderId}")
    public ModelAndView getPaymentDetails(@PathVariable int orderId) {
        List<OrderItem> items = orderItemRepository.findByRestaurantOrderId(orderId);
        double total = items.stream()
                .mapToDouble(item -> {
                    Optional<RestaurantMenu> menu = restaurantMenuRepository.findById(
                            new RestaurantMenuId(item.getRestaurantOrder().getRestaurant(), item.getMenu())
                    );
                    return menu.map(restaurantMenu -> restaurantMenu.getRm_price() * item.getQuantity()).orElse(0.0);
                }).sum();

        ModelAndView modelAndView = new ModelAndView("payment");
        modelAndView.addObject("orderItems", items);
        modelAndView.addObject("totalAmount", total);
        modelAndView.addObject("orderId", orderId);

        System.out.println("Items: " + items);
        System.out.println("Total: " + total);
        System.out.println("Order ID: " + orderId);

        return modelAndView;
    }
}
