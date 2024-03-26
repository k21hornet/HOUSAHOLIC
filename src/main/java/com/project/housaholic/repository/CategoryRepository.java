package com.project.housaholic.repository;

import com.project.housaholic.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String> {
    @Query("SELECT c FROM Category c WHERE c.userId = :userId OR c.userId = '1'")
    List<Category> getCategoryByUserId(String userId);
}
