package com.hotel.api.repository;

import com.hotel.api.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {

    // A custom method to implement searching for a restaurant
    @Query("SELECT r FROM Restaurant r WHERE lower(r.name) LIKE lower(concat('%', :query, '%')) OR lower(r.cuisineType) LIKE lower(concat('%', :query, '%' ))")
    List<Restaurant> findBySearchQuery(String query);

    Restaurant findByOwnerId(UUID ownerId);
}
