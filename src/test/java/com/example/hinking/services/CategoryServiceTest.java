package com.example.hinking.services;

import com.example.hinking.dtos.CategoryDTO;
import com.example.hinking.mappers.CategoryMapper;
import com.example.hinking.models.Category;
import com.example.hinking.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetAllCategoriesWhenNotEmpty() {
        List<Category> categories = new ArrayList<>();
        Category category1 = new Category();
        category1.setCategoryID(1L);
        category1.setName("Category 1");
        Category category2 = new Category();
        category2.setCategoryID(2L);
        category2.setName("Category 2");
        categories.add(category1);
        categories.add(category2);
        when(categoryRepository.findAll()).thenReturn(categories);
        List<CategoryDTO> result = categoryService.getAllCategories();
        assertEquals(2, result.size());
        assertEquals("Category 1", result.get(0).getName());
        assertEquals("Category 2", result.get(1).getName());
    }


    @Test
    void testGetAllCategoriesWhenEmpty() {
        List<Category> emptyCategories = new ArrayList<>();
        when(categoryRepository.findAll()).thenReturn(emptyCategories);
        List<CategoryDTO> result = categoryService.getAllCategories();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }


    @Test
    void testGetCategoryById() {
        Category category = new Category();
        category.setCategoryID(1L);
        category.setName("Category 1");
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        CategoryDTO result = categoryService.getCategoryById(1L);
        assertNotNull(result);
        assertEquals("Category 1", result.getName());
    }

    @Test
    void testGetCategoryByIdWhenNotFound() {
        Long nonExistentId = 999L;
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());
        CategoryDTO result = categoryService.getCategoryById(nonExistentId);
        assertNull(result);
    }
    @Test
    void testCreateCategory() {
        // Arrange
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("New Category");

        Category category = CategoryMapper.toEntity(categoryDTO);

        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        CategoryDTO result = categoryService.createCategory(categoryDTO);

        assertNotNull(result);
        assertEquals("New Category", result.getName());
    }

    @Test
    void testUpdateCategory() {
        Category existingCategory = new Category();
        existingCategory.setCategoryID(1L);
        existingCategory.setName("Old Category");
        System.out.println(existingCategory);

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(existingCategory));

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("Updated Category");

        when(categoryRepository.save(any(Category.class))).thenReturn(existingCategory);

        CategoryDTO result = categoryService.updateCategory(1L, categoryDTO);
        System.out.println(existingCategory);
        assertNotNull(result);
        assertEquals("Updated Category", result.getName());
    }
    @Test
    void testDeleteCategory() {
        Long categoryId = 1L;
        doNothing().when(categoryRepository).deleteById(categoryId);
        categoryService.deleteCategory(categoryId);
        verify(categoryRepository, times(1)).deleteById(categoryId);
    }
}
