package com.hotel.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Table (name = "address")
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    private UUID id;
    private String streetAddress;
    private String city;
    private String stateProvince;
    private String postalCode;
    private String country;

    @PrePersist
    public void generateId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

}
