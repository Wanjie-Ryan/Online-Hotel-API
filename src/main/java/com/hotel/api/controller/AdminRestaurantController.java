package com.hotel.api.controller;

import com.hotel.api.model.Restaurant;
import com.hotel.api.model.User;
import com.hotel.api.request.CreateRestaurantRequest;
import com.hotel.api.response.MessageResponse;
import com.hotel.api.service.RestaurantService;
import com.hotel.api.service.RestaurantServiceImp;
import com.hotel.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.UUID;

// this file is for admins where they create restaurants, and do other admin stuff that concerns restaurants.
@RestController
@RequestMapping("/api/admin/restaurant")
public class AdminRestaurantController {

    private static final Logger logger = LoggerFactory.getLogger(AdminRestaurantController.class);

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
//        logger.info(user.getRole().toString());
        Restaurant hotel = restaurantService.createRestaurant(req, user);


        return new ResponseEntity<>(hotel, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")

    public ResponseEntity<Restaurant> updateHotel(@PathVariable("id") UUID id, @RequestBody CreateRestaurantRequest req, @RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserByJwt(jwt);

        Restaurant hotel = restaurantService.updateRestaurant(id, req);

        return new ResponseEntity<>(hotel, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteHotel(@PathVariable("id") UUID id, @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwt(jwt);
        restaurantService.deleteRestaurant(id);
        MessageResponse response = new MessageResponse();
        response.setMessage("Hotel deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // update hotel status
    @PutMapping("/{id}/status")

    public ResponseEntity<Restaurant> updateHotelStatus(@PathVariable("id") UUID id, @RequestHeader("Authorization") String jwt, @RequestBody CreateRestaurantRequest req) throws Exception{

        User user = userService.findUserByJwt(jwt);
        Restaurant hotel = restaurantService.updateRestaurantStatus(id);
        return new ResponseEntity<>(hotel, HttpStatus.OK);

    }

    // getting the hotel by the owner id

    @GetMapping("/owner")
    public ResponseEntity<Restaurant> getHotelByUserId(@RequestHeader("Authorization") String jwt, @RequestBody CreateRestaurantRequest req) throws Exception{

        User user = userService.findUserByJwt(jwt);
        Restaurant hotel = restaurantService.getRestaurantByUserId(user.getId());
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }
    



}
