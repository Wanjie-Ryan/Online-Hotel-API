package com.hotel.api.service;

import com.hotel.api.dto.RestaurantDto;
import com.hotel.api.model.Address;
import com.hotel.api.model.Restaurant;
import com.hotel.api.model.User;
import com.hotel.api.repository.AddressRepository;
import com.hotel.api.repository.RestaurantRepository;
import com.hotel.api.request.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

// this class implements the RestaurantService interface
@Service
public class RestaurantServiceImp implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepo;

    @Autowired
    private AddressRepository addressRepo;

    @Autowired
    private UserService userService;

    // CREATING  A RESTAURANT
    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {

        Address address = addressRepo.save(req.getAddress());
        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);


        return restaurantRepo.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(UUID id, CreateRestaurantRequest updatedRequest) throws Exception {

        // finding the restaurant by its id
        Restaurant restaurant = findRestaurantById(id);

        if(restaurant.getCuisineType() != null){
            restaurant.setCuisineType(updatedRequest.getCuisineType());
        }

        if(restaurant.getDescription() != null){
            restaurant.setDescription(updatedRequest.getDescription());
        }

        if(restaurant.getName()!=null){
            restaurant.setName(updatedRequest.getName());
        }



        return restaurantRepo.save(restaurant);
    }

    @Override
    public void deleteRestaurant(UUID id) throws Exception {

        Restaurant restaurant = findRestaurantById(id);


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
