package com.example.hinking.controllers;


import com.example.hinking.dtos.CategoryDTO;
import com.example.hinking.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
        public List<CategoryDTO> getAllCategories() {
            return categoryService.getAllCategories();
        }

        @GetMapping("/{id}")
        public CategoryDTO getCategoryById(@PathVariable @Validated Long id) {
            return categoryService.getCategoryById(id);
        }

        @PostMapping
        public CategoryDTO createCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
            return categoryService.createCategory(categoryDTO);
        }

        @PutMapping("/{id}")
        public CategoryDTO updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryDTO categoryDTO) {
            return categoryService.updateCategory(id, categoryDTO);
        }

        @DeleteMapping("/{id}")
        public void deleteCategory(@PathVariable Long id) {
            categoryService.deleteCategory(id);
        }
    }


