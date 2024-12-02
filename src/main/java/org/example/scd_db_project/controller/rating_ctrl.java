package org.example.scd_db_project.controller;

import jakarta.servlet.http.HttpSession;
import org.example.scd_db_project.model.Customer;
import org.example.scd_db_project.model.Restaurant;
import org.example.scd_db_project.model.RestaurantRating;
import org.example.scd_db_project.repository.customer_rep;
import org.example.scd_db_project.repository.restaurant_rep;
import org.example.scd_db_project.repository.restaurantrating_rep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
@RequestMapping("/rating_ctrl")
public class rating_ctrl {
    @Autowired
    private restaurantrating_rep rr_Rep;
    @Autowired
    private customer_rep c_rep;
    @Autowired
    private restaurant_rep r_rep;
    @GetMapping("/rating")
    public String rating(@RequestParam("customerId") Integer customerId,
                         @RequestParam("restaurantId") Integer restaurantId,
                         Model model, HttpSession session) {
        if (customerId == null || restaurantId == null ) {
            return "redirect:/customer_login";
        }
        session.setAttribute("customerId", customerId);
        session.setAttribute("restaurantId", restaurantId);
        model.addAttribute("customerId",customerId);
        model.addAttribute("restaurantId",restaurantId);
        return "rating";
    }

    @PostMapping("/submit_review")
    public String submit_review(@RequestParam("customerId") int customerId,
                                @RequestParam("restaurantId") int restaurantId,
                                @RequestParam("ratingScore") int ratingScore,
                                @RequestParam("reviewText") String reviewText,
                                RedirectAttributes redirectAttributes )
    {

        try{
            System.out.println("Received customerId: " + customerId);
            System.out.println("Received restaurantId: " + restaurantId);
            System.out.println("Received ratingScore: " + ratingScore);
            System.out.println("Received reviewText: " + reviewText);

            RestaurantRating r=new RestaurantRating();
            Customer customer=c_rep.findById(customerId).orElseThrow(() -> new IllegalArgumentException("invalid customer id"));
            Restaurant restaurant=r_rep.findById(restaurantId).orElseThrow(() -> new IllegalArgumentException("invalid restaurant id"));
            r.setCustomer(customer);
            r.setRestaurant(restaurant);
            r.setRating_score(ratingScore);
            r.setReview(reviewText);
            r.setRr_date(new Date());

            rr_Rep.save(r);
            redirectAttributes.addFlashAttribute("message", "Review submitted successfully!");
            return "redirect:/rating_ctrl/reviewSuccess";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error submitting the review. Please try again.");
            return "redirect:/rating_ctrl/rating";
        }
    }
    @GetMapping("/reviewSuccess")
    public String reviewsuccesspage(Model model){
        return "reviewSuccess";
    }

}
