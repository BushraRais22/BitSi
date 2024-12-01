package org.example.scd_db_project.controller;

import jakarta.servlet.http.HttpSession;
import org.example.scd_db_project.model.Customer;
import org.example.scd_db_project.repository.customer_rep;
import org.example.scd_db_project.service.customer_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customer_ctrl")
public class customer_ctrl {
    @Autowired
    private customer_Service c_service;

    @PostMapping("/customer_login")
    public String login(@RequestParam("email") String email, @RequestParam("password") int password, Model model, HttpSession session) {
        System.out.println("Email: " + email + ", Password: " + password);
        Customer customer = c_service.validateLogin(email, password);
        if (customer != null) {
            session.setAttribute("customerId", customer.getC_id());
            System.out.println("Customer logged in with ID: " + customer.getC_id());
            model.addAttribute("message", "Login Successful");
            return "redirect:/restaurant_ctrl/restaurants";
        } else {
            model.addAttribute("message", "Login Failed");
            return "customer_login";
        }
    }
    @PostMapping("/signup")
    public String signup(
            @RequestParam("firstname") String firstname,
            @RequestParam("lname") String lastname,
            @RequestParam("house_no") String houseNo,
            @RequestParam("street_no") String streetNo,
            @RequestParam("area") String area,
            @RequestParam("phone_no") String phoneNo,
            @RequestParam("email") String email,
            @RequestParam("password") int password,
            Model model) {


        // Check if the user already exists
        if (c_service.checkIfEmailExists(email)) {
            model.addAttribute("message", "Email already exists!");
            return "personal_signup"; // Return back to signup page
        }

        // Add user to the database
        c_service.addCustomer(firstname, lastname, houseNo, streetNo, area, phoneNo, email, password);

        // Redirect to home page after successful signup
        model.addAttribute("message", "Signup Successful");
        return "home";
    }

}


