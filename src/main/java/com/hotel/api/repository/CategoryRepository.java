package com.hotel.api.repository;

import com.hotel.api.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    public List<Category> findCategoryByHotelId(UUID id);
}
