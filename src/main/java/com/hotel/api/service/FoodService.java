package com.hotel.api.service;

import com.hotel.api.model.Food;
import com.hotel.api.model.Restaurant;
import com.hotel.api.request.CreateFoodRequest;

import java.util.UUID;

public interface FoodService {

    // method for creating a food
    public Food createFood(CreateFoodRequest req, Restaurant hotel);

    // method for deleting a food
    public void deleteFood(UUID id) throws Exception;

}
