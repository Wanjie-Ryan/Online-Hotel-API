package com.hotel.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

import java.util.UUID;

public class CartItem {

    @Id
    private UUID id;

    @JsonIgnore
    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Food food;


    @PrePersist

    public void generateId(){
        if(this.id == null){
            this.id = UUID.randomUUID();
        }
    }
}
