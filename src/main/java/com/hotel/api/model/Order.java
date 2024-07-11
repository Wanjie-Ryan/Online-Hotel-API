package com.hotel.api.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table (name = "order")
public class Order {

    @Id
    private UUID id;

    @ManyToOne
    // will have a many to one relationship with the user table
    private User customer;

    @ManyToOne
    // one restaurant can have multiple orders
    private Restaurant restaurant;


    @PrePersist

    public void generateId(){
        if(this.id == null){
            this.id = UUID.randomUUID();
        }
    }
}
