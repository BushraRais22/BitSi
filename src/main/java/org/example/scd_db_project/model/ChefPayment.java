package org.example.scd_db_project.model;

import jakarta.persistence.*;

@Entity
@Table(name="chef_payment")
public class ChefPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chefpayment_seq_generator")
    @SequenceGenerator(name = "chefpayment_seq_generator", sequenceName = "CHEFPAYMENT_SEQ", allocationSize = 1)
    private int p_id;

    @OneToOne
    @JoinColumn(name = "co_id" , foreignKey = @ForeignKey(name="fk_chef_payment",
            foreignKeyDefinition = "FOREIGN KEY (co_id) REFERENCES chef_order(co_id) DEFERRABLE INITIALLY DEFERRED"))

    private ChefOrder cheforder;

    @Column(nullable = false)
    private String p_method;

    @Column(nullable = false)
    private String p_status;

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public ChefOrder getCheforder() {
        return cheforder;
    }

    public void setCheforder(ChefOrder cheforder) {
        this.cheforder = cheforder;
    }

    public String getP_method() {
        return p_method;
    }

    public void setP_method(String p_method) {
        this.p_method = p_method;
    }

    public String getP_status() {
        return p_status;
    }

    public void setP_status(String p_status) {
        this.p_status = p_status;
    }
}
