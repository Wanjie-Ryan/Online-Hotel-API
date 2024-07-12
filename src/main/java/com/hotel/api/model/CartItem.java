package com.hotel.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart_item")
public class CartItem {

    @Id
    private UUID id;

    @JsonIgnore
    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Food food;

    private int quantity;

    private List<String> ingredients;

    private Long totalPrice;


    @PrePersist

    public void generateId(){
        if(this.id == null){
            this.id = UUID.randomUUID();
        }
    }
}
