package com.hotel.api.service;

import com.hotel.api.model.Category;
import com.hotel.api.model.Food;
import com.hotel.api.model.Restaurant;
import com.hotel.api.repository.FoodRepo;
import com.hotel.api.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
        // from the food, remove the restaurant
        food.setRestaurant(null);
        foodRepo.delete(food);

    }

    @Override
    public List<Food> getAllFoods(UUID id, boolean isVegetarian, boolean isSeasonal, String category, boolean available) {

        List<Food> foods = foodRepo.findByRestaurantId(id);

        if(isVegetarian){
            // custom method to implement the filter by vegetarian
            foods = filterByvegetarian(foods, isVegetarian);
        }

        if(isSeasonal){
            foods = filterByseasonal(foods, isSeasonal);
        }
        if(category != null && !category.equals("")){
            foods= filterByCategory(foods, category);
        }



        return foods;
    }

    private List<Food> filterByCategory(List<Food> foods, String category) {
        // if we want to apply filters then use streams
        return foods.stream().filter(food->{
            if(food.getFoodCategory()!=null){
                return food.getFoodCategory().getName().equals(category);
            }
            return false;
        }).collect(Collectors.toList());
    }

    private List<Food> filterByseasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(food ->food.isSeasonal() == isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByvegetarian(List<Food> foods, boolean isVegetarian) {
        return foods.stream().filter(food -> food.isVegetarian() == isVegetarian).collect(Collectors.toList());
    }

    @Override
    public Food updateFoodAvailabilityStatus(UUID id) throws Exception {

        Food food = findFoodById(id);
        food.setAvailable(!food.isAvailable());
        return foodRepo.save(food);
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepo.searchFood(keyword);
    }

    @Override
    public Food findFoodById(UUID id) throws Exception {
        Optional<Food> food = foodRepo.findById(id);

        if(food.isEmpty()){
            throw new Exception("Food of the id " + id + " not found");
        }

        return food.get();
    }
}
