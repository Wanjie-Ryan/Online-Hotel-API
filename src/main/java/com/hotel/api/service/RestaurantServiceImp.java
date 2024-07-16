package com.hotel.api.service;

import com.hotel.api.dto.RestaurantDto;
import com.hotel.api.model.Restaurant;
import com.hotel.api.model.User;
import com.hotel.api.repository.RestaurantRepository;
import com.hotel.api.request.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

// this class implements the RestaurantService interface
@Service
public class RestaurantServiceImp implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepo;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {
        return null;
    }

    @Override
    public Restaurant updateRestaurant(UUID id, CreateRestaurantRequest updatedRequest) throws Exception {
        return null;
    }

    @Override
    public void deleteRestaurant(UUID id) throws Exception {

    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return List.of();
    }

    @Override
    public List<Restaurant> searchRestaurant() {
        return List.of();
    }

    @Override
    public Restaurant findRestaurantById(UUID id) throws Exception {
        return null;
    }

    @Override
    public Restaurant getRestaurantByUserId(UUID userId) throws Exception {
        return null;
    }

    @Override
    public RestaurantDto addToFavorites(UUID restaurantId, User user) throws Exception {
        return null;
    }

    @Override
    public Restaurant updateRestaurantStatus(UUID id) throws Exception {
        return null;
    }
}
