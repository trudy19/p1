package com.example.hinking.services;

import com.example.hinking.dtos.CategoryDTO;
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
        return categoryRepository.findById(id).map(CategoryMapper::toDTO).orElse(null);
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = CategoryMapper.toEntity(categoryDTO);
        category = categoryRepository.save(category);
        return CategoryMapper.toDTO(category);
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDetails) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            category.setName(categoryDetails.getName());
            category = categoryRepository.save(category);

            return CategoryMapper.toDTO(category);
        }
        return null;
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }


}

