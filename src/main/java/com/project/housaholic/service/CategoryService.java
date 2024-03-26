package com.project.housaholic.service;

import com.project.housaholic.entity.Category;
import com.project.housaholic.input.CategoryInput;
import com.project.housaholic.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategoriesById(String id) {
        return categoryRepository.getCategoryByUserId(id);
    }

    public void createCategory(CategoryInput categoryInput, String userId){
        Category category = new Category();
        category.setId(UUID.randomUUID().toString());
        category.setCategoryName(categoryInput.getCategoryName());
        category.setIsDefault(0);
        category.setUserId(userId);
        category.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        category.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        categoryRepository.saveAndFlush(category);
    }

    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }
}
