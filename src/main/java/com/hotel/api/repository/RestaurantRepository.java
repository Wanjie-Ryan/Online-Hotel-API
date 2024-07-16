package com.hotel.api.repository;

import com.hotel.api.model.Restaurant;
import com.hotel.api.model.User;

public interface RestaurantRepository {

    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);
}
