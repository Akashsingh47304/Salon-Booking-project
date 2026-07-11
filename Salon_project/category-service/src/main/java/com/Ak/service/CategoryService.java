package com.Ak.service;

import com.Ak.dto.SalonDto;
import com.Ak.model.Category;

import java.util.Set;

public interface CategoryService {

    Category  saveCategory(Category category, SalonDto salonDto);
    Set<Category> getAllCategoryBySalon(Long id);
    Category getCategoryById(Long id);
    void deleteCategory(Long id);

    void deleteCategoryById(Long id, Long salonId);
}
