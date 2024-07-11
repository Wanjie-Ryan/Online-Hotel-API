package com.hotel.api.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Embeddable
public class RestaurantDto {

    private String Title;
    @Column(length = 1000)
    private List<String> images;
    private String description;
    private UUID id;
}
