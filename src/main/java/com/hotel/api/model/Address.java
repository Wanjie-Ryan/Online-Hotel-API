package com.hotel.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table (name = "address")
public class Address {

    @Id
    private UUID id;
}
