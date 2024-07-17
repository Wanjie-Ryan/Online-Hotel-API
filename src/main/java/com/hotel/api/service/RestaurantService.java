package com.hotel.api.service;

import com.hotel.api.dto.RestaurantDto;
import com.hotel.api.model.Restaurant;
import com.hotel.api.model.User;
import com.hotel.api.request.CreateRestaurantRequest;

import java.util.List;
import java.util.UUID;

public interface RestaurantService {

    // CREATING A RESTAURANT
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);

    // UPDATING A RESTAURANT
    public Restaurant updateRestaurant(UUID id, CreateRestaurantRequest updatedRequest) throws Exception;

    // DELETING A RESTAURANT
    public void deleteRestaurant(UUID id) throws Exception;

    // GETTING ALL RESTAURANTS
    public List<Restaurant> getAllRestaurants();

    // SEARCHING A RESTAURANT
    public List<Restaurant> searchRestaurant(String keyword);

    // FINDING THE RESTAURANT BY ITS ID

    public Restaurant findRestaurantById(UUID id) throws Exception;

    // GET RESTAURANT BY USER ID

    public Restaurant getRestaurantByUserId(UUID userId) throws Exception;

    // ADD TO FAVORITES
    public RestaurantDto addToFavorites(UUID restaurantId, User user) throws Exception;

    // UPDATE RESTAURANT STATUS

    public Restaurant updateRestaurantStatus(UUID id) throws Exception;

}
