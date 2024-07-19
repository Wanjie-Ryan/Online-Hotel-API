package com.hotel.api.service;

import com.hotel.api.model.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    // create a category
    public Category createcategory(String name, UUID id);
    public List<Category> findCategoryByHotelId(UUID id) throws Exception;
    public Category findCategoryById(UUID id) throws Exception;

}
