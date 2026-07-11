package com.Ak.controller;

import com.Ak.dto.SalonDto;
import com.Ak.model.Category;
import com.Ak.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories/salon-owner")
public class SalonCategoryController {
    private final CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        SalonDto salonDto = new SalonDto();
        salonDto.setSalonId(1L);
        Category categories =categoryService.saveCategory(category,salonDto);

    return ResponseEntity.ok(categories);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        SalonDto salonDto = new SalonDto();
        salonDto.setSalonId(1L);
        categoryService.deleteCategoryById(id,salonDto.getSalonId());
        return ResponseEntity.ok("category deleted successfully");
    }
}
