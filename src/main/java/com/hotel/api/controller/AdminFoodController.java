package com.hotel.api.controller;

import com.hotel.api.model.Food;
import com.hotel.api.model.Restaurant;
import com.hotel.api.model.User;
import com.hotel.api.request.CreateFoodRequest;
import com.hotel.api.service.FoodService;
import com.hotel.api.service.RestaurantService;
import com.hotel.api.service.UserService;
import jdk.jfr.consumer.RecordingStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;
    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService hotelService;

    public ResponseEntity<Food>  createFood(@RequestBody CreateFoodRequest req, @RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserByJwt(jwt);

        // finding the restaurant by its id, then passing it when creating the food
        Restaurant hotel = hotelService.findRestaurantById(req.getRestaurantId());

        Food food = foodService.createFood(req, req.getCategory(), hotel);
        

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

}
