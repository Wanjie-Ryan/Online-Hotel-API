package com.hotel.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name="restaurant")
public class Restaurant {

    @Id
    private UUID id;

    @OneToOne // only one user per restaurant
    private User owner;

    private String name;
    private String description;
    private String cuisineType;

    @OneToOne
    private Address address;

    @Embedded
    private ContactInformation contactInformation;

    private String openingHours;

    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders = new ArrayList<>();

    @PrePersist

    public void generateId(){
        if(this.id == null){
            this.id = UUID.randomUUID();
        }
    }


}
