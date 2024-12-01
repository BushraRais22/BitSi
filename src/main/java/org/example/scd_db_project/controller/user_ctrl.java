package org.example.scd_db_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class user_ctrl {

    @GetMapping("/")
    public String showWelcomePage() {
        return "welcome";
    }
    @GetMapping("/personal_signup")
    public String showPersonalSignup() {
        return "personal_signup";
    }
    @GetMapping("/customer_login")
    public String showcustomerlogin() {
        return "customer_login";
    }
    @GetMapping("/business_signup")
    public String showbusinessSignup() {
        return "business_signup";
    }
    @GetMapping("/chef_login")
    public String showcheflogin() {
        return "chef_login";
    }
    @GetMapping("/restaurant_login")
    public String showrestaurant_login() {
        return "restaurant_login";
    }
}

