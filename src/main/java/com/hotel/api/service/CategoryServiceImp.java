package com.hotel.api.service;

import com.hotel.api.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImp implements CategoryService{
    @Override
    public Category createcategory(String name, UUID id) {



        return null;
    }

    @Override
    public List<Category> findCategoryByHotelId(UUID id) throws Exception {
        return List.of();
    }

    @Override
    public Category findCategoryById(UUID id) throws Exception {
        return null;
    }
}
