package com.hotel.api.service;

import com.hotel.api.model.Category;
import com.hotel.api.model.Restaurant;
import com.hotel.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImp implements CategoryService{

    @Autowired
    private RestaurantService hotelService;

    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public Category createcategory(String name, UUID id) throws Exception{

        Restaurant hotel = hotelService.getRestaurantByUserId(id);
        Category category = new Category();
        category.setName(name);
        category.setRestaurant(hotel);

        return categoryRepo.save(category);
    }

    @Override
    public List<Category> findCategoryByHotelId(UUID id) throws Exception {

        Restaurant hotel = hotelService.getRestaurantByUserId(id);

        return categoryRepo.findCategoryByHotelId(hotel.getId());
    }

    @Override
    public Category findCategoryById(UUID id) throws Exception {

        Optional<Category> category = categoryRepo.findById(id);

        if(category.isEmpty()){
            throw new Exception("Category not found");
        }

        return category.get();

    }
}
