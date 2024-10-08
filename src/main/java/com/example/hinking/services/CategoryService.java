package com.example.hinking.services;

import com.example.hinking.dtos.CategoryDTO;
import com.example.hinking.exceptions.ResourceNotFoundException;
import com.example.hinking.mappers.CategoryMapper;
import com.example.hinking.models.Category;
import com.example.hinking.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDTO> getAllCategories() {

        return categoryRepository.findAll().stream().map(CategoryMapper::toDTO).collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(Long id) {
        CategoryDTO categoryDTO = categoryRepository.findById(id).map(CategoryMapper::toDTO).orElse(null);
        if (categoryDTO == null) {
            throw new ResourceNotFoundException("category", id);
        }
        return categoryDTO;
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = CategoryMapper.toEntity(categoryDTO);
        category = categoryRepository.save(category);
        return CategoryMapper.toDTO(category);
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDetails) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            throw new ResourceNotFoundException("category", id);
        }
        category.setName(categoryDetails.getName());
        category = categoryRepository.save(category);
        return CategoryMapper.toDTO(category);
    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            throw new ResourceNotFoundException("category", id);
        }
        categoryRepository.deleteById(id);
    }


}

