package com.hotel.api.controller;

import com.hotel.api.model.Restaurant;
import com.hotel.api.model.User;
import com.hotel.api.request.CreateRestaurantRequest;
import com.hotel.api.service.RestaurantService;
import com.hotel.api.service.RestaurantServiceImp;
import com.hotel.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

// this file is for admins where they create restaurants, and do other admin stuff that concerns restaurants.
@RestController
@RequestMapping("/api/admin/restaurant")
public class AdminRestaurantController {

    @Autowired
    private RestaurantServiceImp restServiceImp;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    // creating a method to create a new hotel, the requestBody is to pass in the hotel body, the header is for accessing the jwt token.
    @PostMapping()
    public ResponseEntity<Restaurant> createHotel(@RequestBody CreateRestaurantRequest req, @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwt(jwt);
        Restaurant hotel = restaurantService.createRestaurant(req, user);


        return new ResponseEntity<>(hotel, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")

    public ResponseEntity<Restaurant> updateHotel(@PathVariable("id") UUID id, @RequestBody CreateRestaurantRequest req, @RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserByJwt(jwt);

        Restaurant hotel = restaurantService.updateRestaurant(id, req);

        return new ResponseEntity<>(hotel, HttpStatus.OK);

    }

}