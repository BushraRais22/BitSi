package org.example.scd_db_project.controller;

import jakarta.servlet.http.HttpSession;
import org.example.scd_db_project.model.*;
import org.example.scd_db_project.repository.chef_rep;
import org.example.scd_db_project.repository.cheforder_rep;
import org.example.scd_db_project.repository.chefrating_rep;
import org.example.scd_db_project.repository.customer_rep;
import org.example.scd_db_project.service.chef_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/chef_ctrl")
public class chef_ctrl {
    @Autowired
    private chef_service chefservice;
    @Autowired
    private customer_rep c_rep;
    @Autowired
    private chef_rep ch_rep;
    @Autowired
    private cheforder_rep cheforder_rep;
    @Autowired
    private chefrating_rep chefrating_rep;
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
    @GetMapping("/chefs")
    public String getAllChefs(Model model,HttpSession session) {
        List<Chef> chefs=chefservice.getALlChefs();
        model.addAttribute("chefs", chefs);

        return "chef_profile";
    }
    @GetMapping("/chef_order/{id}")
    public String getChefOrders(@PathVariable int id, Model model, HttpSession session) {
        Integer customerId= (Integer) session.getAttribute("customerId");
        if(customerId==null){
            return "redirect:/customer_login";
        }
        model.addAttribute("chefId", id);
        model.addAttribute("customerId", customerId);
        return "chef_order";
    }
    @PostMapping("placeOrder")
    public String placeOrder(@RequestParam("orderDetails") String orderDetails,
                             @RequestParam("chefId") int chefId,
                             Model model, HttpSession session ,
                             RedirectAttributes redirectAttributes){
    try{
        Integer customerId= (Integer) session.getAttribute("customerId");
        if(customerId==null){
            return "redirect:/customer_ctrl/customer_login";
        }
        Customer customer=c_rep.findById(customerId).orElseThrow(()-> new IllegalArgumentException("Customer Not Found"));
        Chef chef=ch_rep.findById(chefId).orElseThrow(()-> new IllegalArgumentException("Chef Not Found"));
        ChefOrder chefOrder=new ChefOrder();
        chefOrder.setCustomer(customer);
        chefOrder.setStatus("pending");
        chefOrder.setChef(chef);
        chefOrder.setSpecifications(orderDetails);
        chefOrder.setTotal_amount(0.0);
        chefOrder.setCo_date(new Date());
        cheforder_rep.save(chefOrder);
        redirectAttributes.addFlashAttribute("message","Order placed Successfully");
        return "chef_payment";
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("message","Order placement Failed");
        return "redirect:/chef_ctrl/chefs";
    }
    }
    @GetMapping("/review")
    public String getReview(@RequestParam("customerId") Integer customerId,
                            @RequestParam("chefId") Integer chefId,
                            Model model, HttpSession session) {
        if (customerId == null || chefId == null) {
            return "redirect:/customer_ctrl/customer_login";
        }
        session.setAttribute("customerId", customerId);
        session.setAttribute("chefId", chefId);
        model.addAttribute("customerId",customerId);
        model.addAttribute("chefId",chefId);
        return "chefRating";
    }
    @PostMapping("/submit_review")
    public String submit_review(@RequestParam("customerId") int customerId,
                                @RequestParam("chefId") int chefId,
                                @RequestParam("ratingScore") int ratingScore,
                                @RequestParam("reviewText") String reviewText,
                                RedirectAttributes redirectAttributes )
    {

        try{
            System.out.println("Received customerId: " + customerId);
            System.out.println("Received chefId: " + chefId);
            System.out.println("Received ratingScore: " + ratingScore);
            System.out.println("Received reviewText: " + reviewText);

            ChefRating  chefRating=new ChefRating();
            Customer customer=c_rep.findById(customerId).orElseThrow(() -> new IllegalArgumentException("invalid customer id"));
            Chef chef=ch_rep.findById(chefId).orElseThrow(()-> new IllegalArgumentException("Chef Not Found"));
            chefRating.setChef(chef);
            chefRating.setReview(reviewText);
            chefRating.setCustomer(customer);
            chefRating.setRating_score(ratingScore);
            chefRating.setCr_date(new Date());
            chefrating_rep.save(chefRating);
            redirectAttributes.addFlashAttribute("message", "Review submitted successfully!");
            return "redirect:/chef_ctrl/feedbackSuccess";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error submitting the review. Please try again.");
            return "redirect:/chef_ctrl/submit_review";
        }
    }
    @GetMapping("/feedbackSuccess")
    public String feedbacksuccesspage(Model model){
        return "feedbackSuccess";
    }
//

}
