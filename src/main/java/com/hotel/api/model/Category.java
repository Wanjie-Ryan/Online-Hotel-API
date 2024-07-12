package com.hotel.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {

    @Id
    private UUID id;

    private String name;

    @ManyToOne
    @JsonIgnore
    private Restaurant restaurant;



    @PrePersist

    public void generateId(){
        if(this.id == null){
            this.id = UUID.randomUUID();
        }
    }
}
