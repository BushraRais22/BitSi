package org.example.scd_db_project.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.scd_db_project.model.Customer;
import org.example.scd_db_project.model.User;
import org.example.scd_db_project.repository.customer_rep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class customer_Service {
    @Autowired
    private customer_rep c_rep;
    public Customer validateLogin(String email, int password) {
        if(c_rep.findByEmail(email).isPresent()) {
            Customer c = c_rep.findByEmail(email).get();
            if(c.getPasswordd() == password) {
                return c_rep.findByEmail(email).get();
            }
        }
       return null;
    }
    public boolean checkIfEmailExists(String email) {
        return c_rep.findByEmail(email).isPresent();
    }

    public Customer addCustomer(String firstname, String lastname, String houseNo, String streetNo, String area,
                            String phoneNo, String email, int password) {
        Customer new_c = new Customer();
        new_c.setC_firstname(firstname);
        new_c.setC_lastname(lastname);
        new_c.setHouse_no(houseNo);
        new_c.setStreet_no(streetNo);
        new_c.setArea(area);
        new_c.setPhone(phoneNo);
        new_c.setEmail(email);
        new_c.setPasswordd(password);

        User u = new User();
        u.setUser_id(1);
        new_c.setU(u);

        return c_rep.save(new_c);
    }
}
