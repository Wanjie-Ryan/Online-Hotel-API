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

@RestController
@RequestMapping
public class FoodController {

    @Autowired
    private UserService userService;

    @Autowired
    private FoodService foodService;


    @GetMapping("/search")

    public ResponseEntity<List<Food>> searchFood(@RequestParam("keyword") String keyword, @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwt(jwt);
        List<Food> foundFoods = foodService.searchFood(keyword);
        return new ResponseEntity<>(foundFoods, HttpStatus.OK);
    }

}
