package org.example.scd_db_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class payment_ctrl {
    @GetMapping("/payment")
    public String payment(Model model) {
        return "payment";
    }
}
