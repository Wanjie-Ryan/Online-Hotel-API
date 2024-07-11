package com.hotel.api.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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




}
