package com.hotel.api.service;

import com.hotel.api.model.Food;
import com.hotel.api.model.Restaurant;
import com.hotel.api.request.CreateFoodRequest;
import com.hotel.api.request.CreateRestaurantRequest;

import java.util.List;
import java.util.UUID;

public interface FoodService {

    // method for creating a food
    public Food createFood(CreateFoodRequest req, Restaurant hotel);

    // method for deleting a food
    public void deleteFood(UUID id) throws Exception;

    // getting all foods, we are passing in the attributes so that the user can be able to filter the foods
    public List<Food> getAllFoods(UUID id, boolean isVegetarian, boolean isSeasonal, String category, boolean available);

    // update a food
    public Food updateFoodAvailabilityStatus(UUID id) throws Exception;

    // search food
    public List<Food> searchFood(String keyword);

    // find food by id
    public Food findFoodById(UUID id)throws Exception;

    //



}
