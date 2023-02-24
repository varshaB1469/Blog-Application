package com.springboot.blog.services;

import com.springboot.blog.dtos.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto,Long categoryId);

    void deleteCategory(Long categoryId);

    CategoryDto getSingleCategory(Long categoryId);

    List<CategoryDto> allCategories();


}
