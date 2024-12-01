package org.example.scd_db_project.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class rating_ctrl {
    @GetMapping("/rating")
    public String rating(Model model, HttpSession session) {
        Integer customerId=getCurrentCustomerId(session);
        if (customerId == null) {
            // Handle the case where the customer ID isn't found, maybe redirect to login
            return "redirect:/customer_login";
        }
        model.addAttribute("customerId",customerId);
        return "rating";
    }
    private Integer getCurrentCustomerId(HttpSession session) {
        return (Integer) session.getAttribute("customerId");

    }
}
