package org.example.scd_db_project.model;

import jakarta.persistence.*;

@Entity
@Table(name="chef")
public class Chef {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chef_seq_generator")
    @SequenceGenerator(name = "chef_seq_generator", sequenceName = "CHEF_SEQ", allocationSize = 1)
    private int ch_id;

    @Column(nullable = true)
    private String ch_firstname;

    @Column(nullable = false)
    private String ch_lastname;

    @Column(nullable = false)
    private String ch_phone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private int passwordd;

    @Column(nullable = false)
    private String speciality;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name="fk_user_chef",
            foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES users(user_id) DEFERRABLE INITIALLY DEFERRED"))
    private User user;

    public int getCh_id() {
        return ch_id;
    }

    public void setCh_id(int ch_id) {
        this.ch_id = ch_id;
    }

    public String getCh_firstname() {
        return ch_firstname;
    }

    public int getPasswordd() {
        return passwordd;
    }

    public void setPasswordd(int passwordd) {
        this.passwordd = passwordd;
    }

    public void setCh_firstname(String ch_firstname) {
        this.ch_firstname = ch_firstname;
    }

    public String getCh_lastname() {
        return ch_lastname;
    }

    public void setCh_lastname(String ch_lastname) {
        this.ch_lastname = ch_lastname;
    }

    public String getCh_phone() {
        return ch_phone;
    }

    public void setCh_phone(String ch_phone) {
        this.ch_phone = ch_phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String ch_email) {
        this.email = ch_email;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
