package com.hotel.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table (name = "order")
public class Order {

    @Id
    private UUID id;

    @ManyToOne
    // will have a many to one relationship with the user table
    private User customer;

    @JsonIgnore
    @ManyToOne
    // one restaurant can have multiple orders
    private Restaurant restaurant;

    private Long totalAmount;

    private String orderStatus;

    private Date createdAt;

    @ManyToOne
    private Address deliverAddress;

    @OneToMany
    private List<OrderItem> items;


    @PrePersist

    public void generateId(){
        if(this.id == null){
            this.id = UUID.randomUUID();
        }
    }
}
