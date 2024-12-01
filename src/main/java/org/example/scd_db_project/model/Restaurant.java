package org.example.scd_db_project.model;

import jakarta.persistence.*;

@Entity
@Table(name="restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_seq_generator")
    @SequenceGenerator(name = "restaurant_seq_generator", sequenceName = "RESTAURANT_SEQ", allocationSize = 1)
    private int idd;

    @Column(nullable = false)
    private String r_name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String r_restaurant_type;

    @Column(nullable = false)
    private String r_phone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private int passwordd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" , foreignKey = @ForeignKey(name="fk_user_restaurant",
            foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES users(user_id) DEFERRABLE INITIALLY DEFERRED"))

    private User user;

    public int getIdd() {
        return idd;
    }

    public void setIdd(int r_id) {
        this.idd = r_id;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public String getLocation() {
        return location;
    }

    public int getPasswordd() {
        return passwordd;
    }

    public void setPasswordd(int passwordd) {
        this.passwordd = passwordd;
    }

    public void setLocation(String r_loaction) {
        this.location = r_loaction;
    }

    public String getR_restaurant_type() {
        return r_restaurant_type;
    }

    public void setR_restaurant_type(String r_restaurant_type) {
        this.r_restaurant_type = r_restaurant_type;
    }

    public String getR_phone() {
        return r_phone;
    }

    public void setR_phone(String r_phone) {
        this.r_phone = r_phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String r_email) {
        this.email = r_email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
