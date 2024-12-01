package org.example.scd_db_project.service;

import org.example.scd_db_project.model.Chef;
import org.example.scd_db_project.model.Customer;
import org.example.scd_db_project.model.Restaurant;
import org.example.scd_db_project.model.User;
import org.example.scd_db_project.repository.chef_rep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class chef_service {
    @Autowired
    private chef_rep ch_rep;
    public boolean CheckEmail(String email) {
        return ch_rep.findByEmail(email).isPresent();
    }
    public void aadchef(String email, int password, String first_name, String last_name, String phone,String speciality){
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
        ch_rep.save(c);
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

}
