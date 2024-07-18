package com.hotel.api.repository;

import com.hotel.api.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface FoodRepo extends JpaRepository<Food, UUID> {

    public List<Food> findByRestaurantId(UUID id);
    @Query("SELECT f FROM Food f WHERE f.name LIKE %:keyword% OR f.foodCategory.name LIKE %:keyword%")
    public List<Food> searchFood(@Param("keyword") String keyword);
}
