package com.hotel.api.service;

import com.hotel.api.model.Category;
import com.hotel.api.model.Food;
import com.hotel.api.model.Restaurant;
import com.hotel.api.repository.FoodRepo;
import com.hotel.api.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FoodServiceImp implements FoodService {

    @Autowired
    private FoodRepo foodRepo;

    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant hotel) {

        Food food = new Food();
        food.setFoodCategory(category);
        food.setRestaurant(hotel);
        food.setDescription(req.getDescription());
        food.setImages(req.getImages());
        food.setName(req.getName());
        food.setPrice(req.getPrice());
        food.setIngredients(req.getIngredients());
        food.setSeasonal(req.isSeasonal());
        food.setVegetarian(req.isVegetarian());

        Food savedFood = foodRepo.save(food);
        hotel.getFood().add(savedFood);

        return savedFood;
    }

    @Override
    public void deleteFood(UUID id) throws Exception {

        Food food = findFoodById(id);
        food.setRestaurant(null);
        foodRepo.delete(food);

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
