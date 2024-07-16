package com.hotel.api.repository;

import com.hotel.api.model.Restaurant;
import com.hotel.api.model.User;
import com.hotel.api.request.CreateRestaurantRequest;

public interface RestaurantRepository {

    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);
}
