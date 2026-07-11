package com.Ak.service;

import com.Ak.dto.SalonDto;
import com.Ak.model.Category;
import com.Ak.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    @Override
    public Category saveCategory(Category category, SalonDto salonDto) {
        Category newCategory= new Category();
        newCategory.setName(category.getName());
        newCategory.setImage(category.getImage());
        newCategory.setSalonId(salonDto.getSalonId());
        log.info("Saving new category {} to the database", newCategory.getName());
        return categoryRepository.save(newCategory);
    }

    @Override
    public Set<Category> getAllCategoryBySalon(Long id) {
        log.info("Getting all categories for salon {}", id);
        log.info("search");
       return categoryRepository.findBySalonId(id);
    }

    @Override
    public Category getCategoryById(Long id) {
       Category category= categoryRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Category not found"));
       log.info("Getting category with id {}", id);
       return category;
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        categoryRepository.delete(category);
        log.info("Deleted category with id {}", id);

    }

    @Override
    public void deleteCategoryById(Long id, Long salonId) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        if (!category.getSalonId().equals(salonId)) {
            throw new RuntimeException("You are not authorized to delete this category");
        }
        categoryRepository.delete(category);
        log.info("Deleted category with id {} for salon {}", id, salonId);
    }
}
