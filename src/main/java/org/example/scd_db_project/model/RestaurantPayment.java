package org.example.scd_db_project.model;

import jakarta.persistence.*;

@Entity
@Table(name="restaurant_payment")
public class RestaurantPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "respayment_seq_generator")
    @SequenceGenerator(name = "respayment_seq_generator", sequenceName = "RESPAYMENT_SEQ", allocationSize = 1)
    private int p_id;

    @OneToOne
    @JoinColumn(name = "ro_id" , foreignKey = @ForeignKey(name="fk_res_order_payment",
            foreignKeyDefinition = "FOREIGN KEY (ro_id) REFERENCES restaurant_order(ro_id) DEFERRABLE INITIALLY DEFERRED"))
    private RestaurantOrder restaurantorder;

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

    public RestaurantOrder getRestaurantorder() {
        return restaurantorder;
    }

    public void setRestaurantorder(RestaurantOrder restaurantorder) {
        this.restaurantorder = restaurantorder;
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
