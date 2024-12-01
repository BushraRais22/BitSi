package org.example.scd_db_project.service;

import org.example.scd_db_project.model.User;
import org.example.scd_db_project.repository.user_rep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;


@Service
public class user_service {

    @Autowired
    private user_rep userRepository;

    @PostConstruct
    public void insertdata() {
        if (userRepository.count()==0) {
            User customer = new User();
            customer.setRole("customer");

            User chef = new User();
            chef.setRole("chef");

            User restaurant = new User();
            restaurant.setRole("restaurant");

            userRepository.save(customer);
            userRepository.save(chef);
            userRepository.save(restaurant);
        }
        else{
            System.out.println("Users already exist.");
        }
    }
}
