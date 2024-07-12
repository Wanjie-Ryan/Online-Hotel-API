package com.hotel.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {


    @Id
    private UUID id;

    @ManyToOne
    private Food food;

    private int quantity;

    private Long totalPrice;

    private List<String> ingredients;


    @PrePersist

    public void generateId(){
        if(this.id == null){
            this.id = UUID.randomUUID();
        }
    }
}
