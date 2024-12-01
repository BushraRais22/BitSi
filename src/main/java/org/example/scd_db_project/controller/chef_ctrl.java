package org.example.scd_db_project.controller;

import org.example.scd_db_project.model.Chef;
import org.example.scd_db_project.model.Customer;
import org.example.scd_db_project.service.chef_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/chef_ctrl")
public class chef_ctrl {
    @Autowired
    private chef_service chefservice;
    @PostMapping("/signup")
    public String signup(
            @RequestParam ("ch_firstname") String first_name,
            @RequestParam ("ch_lastname") String last_name,
            @RequestParam("email") String email,
            @RequestParam("password") int password,
            @RequestParam("ch_phone") String phone,
            @RequestParam("ch_speciality") String speciality, Model model
    ){
        if(chefservice.CheckEmail(email)){
            model.addAttribute("message","Email Already Exists");
            return "business_signup";
        }
        chefservice.aadchef(email, password, first_name, last_name, phone, speciality);
        model.addAttribute("message","Signup Successful");
        return "chef_homepage";
    }
    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") int password, Model model) {
        Chef c = chefservice.validateLogin(email, password);
        if (c != null) {
            model.addAttribute("message", "Login Successful");
            return "chef_homepage";
        } else {
            model.addAttribute("message", "Login Failed");
            return "chef_login";
        }
    }
}
