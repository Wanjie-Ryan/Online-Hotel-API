package com.hotel.api.request;

import com.hotel.api.model.Category;
import com.hotel.api.model.IngredientsItem;
import lombok.Data;

import java.util.List;
import java.util.UUID;

// this is just a class that abstracts from the actual Food Model, like you just pick the attributes that you want to pass them through your API.
@Data
public class CreateFoodRequest {
    private UUID id;
    private String name;
    private String description;
    private Long Price;
    private Category category;
    private List<String> images;
    private UUID restaurantId;
    private boolean isVegetarian;
    private boolean isSeasonal;
    private List<IngredientsItem> ingredients;

}
