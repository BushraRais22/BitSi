package org.example.scd_db_project.model;

import jakarta.persistence.*;

@Entity
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq_generator")
    @SequenceGenerator(name = "customer_seq_generator", sequenceName = "CUSTOMER_SEQ", allocationSize = 1)
    private int c_id;
    @Column(nullable = false)
    private String c_firstname;
    @Column(nullable = false)
    private String c_lastname;
    @Column(nullable = false)
    private String house_no;
    @Column(nullable = false)
    private String street_no;
    @Column(nullable = false)
    private String area;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private int passwordd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" , foreignKey = @ForeignKey(name="fk_user_customer",
            foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES users(user_id) DEFERRABLE INITIALLY DEFERRED"))

    private User user;

    public User getU() {
        return user;
    }

    public void setU(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public int getPasswordd() {
        return passwordd;
    }

    public void setPasswordd(int passwordd) {
        this.passwordd = passwordd;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStreet_no() {
        return street_no;
    }

    public void setStreet_no(String street_no) {
        this.street_no = street_no;
    }

    public String getHouse_no() {
        return house_no;
    }

    public void setHouse_no(String house_no) {
        this.house_no = house_no;
    }

    public String getC_lastname() {
        return c_lastname;
    }

    public void setC_lastname(String c_lastname) {
        this.c_lastname = c_lastname;
    }

    public String getC_firstname() {
        return c_firstname;
    }

    public void setC_firstname(String c_firstname) {
        this.c_firstname = c_firstname;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }
}
