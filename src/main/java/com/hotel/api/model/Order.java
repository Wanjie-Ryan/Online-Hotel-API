package com.hotel.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table (name = "order")
@Data
@NoArgsConstructor
@AllArgsConstructor
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



    private int totalItem;

    private int totalPrice;


    @PrePersist

    public void generateId(){
        if(this.id == null){
            this.id = UUID.randomUUID();
        }
    }
}
