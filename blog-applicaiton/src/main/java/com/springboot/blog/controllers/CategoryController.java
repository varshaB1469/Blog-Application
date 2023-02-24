package com.springboot.blog.controllers;


import com.springboot.blog.dtos.CategoryDto;
import com.springboot.blog.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto addedCategory = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(addedCategory, HttpStatus.CREATED);
    }

    @PutMapping("/categoryId/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Long categoryId){
        CategoryDto savedCategory = categoryService.updateCategory(categoryDto,categoryId);
        return new ResponseEntity<>(savedCategory,HttpStatus.OK);
    }


    @DeleteMapping("/categoryId/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("category deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/categoryId/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long categoryId){
        CategoryDto cat = categoryService.getSingleCategory(categoryId);
        return new ResponseEntity<>(cat,HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryDto>> getAll(){
        List<CategoryDto> listOfCategories = categoryService.allCategories();
        return new ResponseEntity<>(listOfCategories,HttpStatus.OK);
    }
}
