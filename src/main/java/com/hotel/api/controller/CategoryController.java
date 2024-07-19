package com.hotel.api.controller;

import com.hotel.api.model.Category;
import com.hotel.api.model.User;
import com.hotel.api.service.CategoryService;
import com.hotel.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Category> createcategory(@RequestBody Category category, @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwt(jwt);
        Category cat = categoryService.createcategory(category.getName(), user.getId());

        return new ResponseEntity<>(cat, HttpStatus.CREATED);
    }
}
