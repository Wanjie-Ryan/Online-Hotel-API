package com.hotel.api.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
//This annotation indicates that this class is meant to be embedded in an entity
@Embeddable
public class RestaurantDto {

    private String Title;
    @Column(length = 1000) // Specifies the length of the images field in the database.
    private List<String> images;
    private String description;
    private UUID id;
}
