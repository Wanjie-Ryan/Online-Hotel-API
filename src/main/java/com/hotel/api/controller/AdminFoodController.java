package com.hotel.api.controller;

import com.hotel.api.model.Food;
import com.hotel.api.model.Restaurant;
import com.hotel.api.model.User;
import com.hotel.api.request.CreateFoodRequest;
import com.hotel.api.response.MessageResponse;
import com.hotel.api.service.FoodService;
import com.hotel.api.service.RestaurantService;
import com.hotel.api.service.UserService;
import jdk.jfr.consumer.RecordingStream;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;
    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService hotelService;

    @PostMapping
    public ResponseEntity<Food>  createFood(@RequestBody CreateFoodRequest req, @RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserByJwt(jwt);

        // finding the restaurant by its id, then passing it when creating the food
        Restaurant hotel = hotelService.findRestaurantById(req.getRestaurantId());

        Food food = foodService.createFood(req, req.getCategory(), hotel);
        

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")

    public ResponseEntity <MessageResponse> deleteFood(@PathVariable("id") UUID id, @RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserByJwt(jwt);

        foodService.deleteFood(id);
        MessageResponse message = new MessageResponse();
        message.setMessage("Food deleted successfully");

        return new ResponseEntity<>(message, HttpStatus.OK);

    }

    @PutMapping("/{id")

    public ResponseEntity<Food> updateFoodAvailabilityStatus(@PathVariable("id") UUID id, @RequestHeader("Authorization") String jwt)throws Exception{

        User user = userService.findUserByJwt(jwt);
        Food food = foodService.updateFoodAvailabilityStatus(id);

        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @GetMapping("/search")

    public ResponseEntity <List<Food>> searchFood(@RequestParam("keyword") String keyword, @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwt(jwt);
        List<Food> foundFoods = foodService.searchFood(keyword);
        return new ResponseEntity<>(foundFoods, HttpStatus.OK);
    }

}
