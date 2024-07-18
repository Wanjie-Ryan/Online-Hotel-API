package com.hotel.api.repository;

import com.hotel.api.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FoodRepo extends JpaRepository<Food, UUID> {

    public List<Food> findByRestaurantId(UUID id);
}
