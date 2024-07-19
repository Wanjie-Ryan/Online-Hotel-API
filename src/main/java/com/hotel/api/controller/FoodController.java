package com.hotel.api.controller;

import com.hotel.api.model.Food;
import com.hotel.api.model.User;
import com.hotel.api.service.FoodService;
import com.hotel.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private UserService userService;

    @Autowired
    private FoodService foodService;


    @GetMapping("/search")

    public ResponseEntity<List<Food>> searchFood(@RequestParam String keyword, @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwt(jwt);
        List<Food> foundFoods = foodService.searchFood(keyword);
        return new ResponseEntity<>(foundFoods, HttpStatus.OK);
    }

    // finding the food for the hotel
    @GetMapping("/hotel/{hotelId")

    public ResponseEntity<List<Food>> getHotelFoods(@PathVariable("hotelId") UUID hotelId, @RequestParam boolean vegetarian, @RequestParam boolean seasonal, @RequestParam(required = false) String food_category, @RequestParam boolean available ,@RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwt(jwt);

        List<Food> foods = foodService.getAllFoods(hotelId, vegetarian, seasonal, food_category, available);


        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

}
