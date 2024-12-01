package org.example.scd_db_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class rating_ctrl {
    @GetMapping("/rating")
    public String rating(Model model) {
        return "rating";
    }
}
