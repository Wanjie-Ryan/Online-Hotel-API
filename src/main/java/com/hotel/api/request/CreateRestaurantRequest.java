package com.hotel.api.request;

import com.hotel.api.model.Address;
import com.hotel.api.model.ContactInformation;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CreateRestaurantRequest {
    private UUID id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;


}
