package com.hotel.api.controller;

import com.hotel.api.model.Restaurant;
import com.hotel.api.model.User;
import com.hotel.api.service.RestaurantService;
import com.hotel.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-restaurant")
public class UserRestaurantController {

    @Autowired
    private RestaurantService hotelService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    // this method will return a list of hotels
    public ResponseEntity<List<Restaurant>> SearchHotel(@RequestHeader("Authorization") String jwt, @RequestParam String keyword) throws  Exception{

        User user = userService.findUserByJwt(jwt);
        List<Restaurant> hotel = hotelService.searchRestaurant(keyword);

        return new ResponseEntity<>(hotel, HttpStatus.OK);

    }
}
