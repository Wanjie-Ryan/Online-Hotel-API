package com.hotel.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name="food")
public class Food {

    @Id
    private UUID id;

    private String name;
    private String description;
    private Long price;

    @ManyToOne
    private Category foodCategory;


    @PrePersist

    public void generateId(){
        if(this.id == null){
            this.id = UUID.randomUUID();
        }
    }

}
