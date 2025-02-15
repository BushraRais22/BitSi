package org.example.scd_db_project.service;

import org.example.scd_db_project.model.*;
import org.example.scd_db_project.repository.chef_rep;
import org.example.scd_db_project.repository.cheforder_rep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class chef_service {
    @Autowired
    private chef_rep ch_rep;
    @Autowired
    private cheforder_rep cheforder_rep;

    public boolean CheckEmail(String email) {
        return ch_rep.findByEmail(email).isPresent();
    }
    public Chef aadchef(String email, int password, String first_name, String last_name, String phone,String speciality){
        Chef c=new Chef();
        c.setEmail(email);
        c.setCh_firstname(first_name);
        c.setCh_lastname(last_name);
        c.setCh_phone(phone);
        c.setPasswordd(password);
        c.setSpeciality(speciality);
        User u=new User();
        u.setUser_id(2);
        c.setUser(u);
        return ch_rep.save(c);
    }
    public Chef validateLogin(String email, int password) {
        if(ch_rep.findByEmail(email).isPresent()) {
            Chef c = ch_rep.findByEmail(email).get();
            if(c.getPasswordd() == password) {
                return ch_rep.findByEmail(email).get();
            }
        }
        return null;
    }
    public List<Chef> getALlChefs(){
        List<Chef> chefs=ch_rep.findAll();
        return chefs;
    }

    public List<ChefOrder> getOrdersByChef(Integer chef_id) {
        return cheforder_rep.findOrdersByChefId(chef_id);
    }

    public void updateOrderStatus(int orderId, String newStatus) {
        ChefOrder order = cheforder_rep.findById(orderId).orElseThrow(()-> new IllegalStateException("Order Not Found")    );
        order.setStatus(newStatus);
        cheforder_rep.save(order);
    }
}
