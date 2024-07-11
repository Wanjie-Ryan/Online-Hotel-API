package com.hotel.api.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table (name = "user")
public class User {

    @Id
    private UUID id;
    private String name;
    private String email;
    private String password;
    private User_Role role;

    // define the relation the user has to the Orders table
    @OneToMany
    // the json ignore is used to ignore the orders field when we are getting the users, a separate call will be made to fetch the orders
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    @ElementCollection
    private List<RestaurantDto> favorites = new ArrayList<>();

    // we will store the addresses of the users such that the user will not have to input the address again when he wants to make an order again
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    // the purpose for the cascade is that lets say the user is deleted, then all the addresses related to that user will be deleted too.
    private List<Address> addresses = new ArrayList<>();




}
