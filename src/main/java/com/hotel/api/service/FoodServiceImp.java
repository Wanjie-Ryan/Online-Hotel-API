package com.hotel.api.service;

import com.hotel.api.model.Food;
import com.hotel.api.model.Restaurant;
import com.hotel.api.request.CreateFoodRequest;

import java.util.List;
import java.util.UUID;

public class FoodServiceImp implements FoodService {


    @Override
    public Food createFood(CreateFoodRequest req, Restaurant hotel) {
        return null;
    }

    @Override
    public void deleteFood(UUID id) throws Exception {

    }

    @Override
    public List<Food> getAllFoods(UUID id, boolean isVegetarian, boolean isSeasonal, String category, boolean available) {
        return List.of();
    }

    @Override
    public Food updateFoodAvailabilityStatus(UUID id) throws Exception {
        return null;
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return List.of();
    }

    @Override
    public Food findFoodById(UUID id) throws Exception {
        return null;
    }
}
