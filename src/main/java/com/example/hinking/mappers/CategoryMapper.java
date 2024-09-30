package com.example.hinking.mappers;

import com.example.hinking.dtos.CategoryDTO;
import com.example.hinking.models.Category;

public class CategoryMapper {
    public static CategoryDTO toDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setCategoryID(category.getCategoryID());
        dto.setName(category.getName());
        return dto;
    }

    public static Category toEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        return category;
    }
}
