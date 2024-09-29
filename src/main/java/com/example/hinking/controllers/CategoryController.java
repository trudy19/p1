package com.example.hinking.controllers;


import com.example.hinking.models.Category;
import com.example.hinking.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import java.util.List;


    @RestController
    @RequestMapping("/api/categories")
    public class CategoryController {

        @Autowired
        private CategoryService categoryService;

        @GetMapping
        public List<Category> getAllCategories() {
            return categoryService.getAllCategories();
        }

        @GetMapping("/{id}")
        public Category getCategoryById(@PathVariable Long id) {
            return categoryService.getCategoryById(id);
        }

        @PostMapping
        public Category createCategory(@RequestBody Category category) {
            return categoryService.createCategory(category);
        }

        @PutMapping("/{id}")
        public Category updateCategory(@PathVariable Long id, @RequestBody Category category) {
            return categoryService.updateCategory(id, category);
        }

        @DeleteMapping("/{id}")
        public void deleteCategory(@PathVariable Long id) {
            categoryService.deleteCategory(id);
        }
    }


