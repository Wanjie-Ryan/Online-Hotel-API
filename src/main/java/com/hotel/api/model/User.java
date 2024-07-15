package com.hotel.api.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotel.api.dto.RestaurantDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity // map this java class to our user table in the db.
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "users")
public class User {

    @Id
    private UUID id;
    private String name;
    private String email;
    // making the password not to be sent as a response back to the user for security reasons obv
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    // in the role, we will give a default role of customer
    private User_Role role = User_Role.Customer;

    // define the relation the user has to the Orders table
    // the mappedBy tells spring boot that it should not create a new table for the orders but rather use the orders table that has already been defined
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    // the json ignore is used to ignore the orders field when we are getting the users, a separate call will be made to fetch the orders
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    // Specifies a collection of basic or embeddable
    // The favorites field is annotated with @ElementCollection, meaning it is a collection of RestaurantDto objects that are stored in a separate table, but their lifecycle is tied to the User entity.
    //When a User entity is persisted, the favorites collection is also persisted.
    //If a User entity is deleted, the favorites collection is also deleted.types.
    @ElementCollection

    //Yes, you can use @OneToMany if RestaurantDto represents an entity with its own identity. 

    private List<RestaurantDto> favorites = new ArrayList<>();

    // we will store the addresses of the users such that the user will not have to input the address again when he wants to make an order again.
    // cascade options define the operations to propagate from parent to child entities.
    //orphanRemoval ensures that child entities are deleted if they are no longer referenced by the parent entity.
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    // the purpose for the cascade is that lets say the user is deleted, then all the addresses related to that user will be deleted too.
    private List<Address> addresses = new ArrayList<>();

    // This method is called before the entity is persisted (saved) to the database.
    @PrePersist

    public void generateId(){
        if(this.id == null){
            this.id = UUID.randomUUID();
        }
    }




}
