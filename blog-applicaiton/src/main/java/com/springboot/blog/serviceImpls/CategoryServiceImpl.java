package com.springboot.blog.serviceImpls;

import com.springboot.blog.dtos.CategoryDto;
import com.springboot.blog.exceptions.ResourceNotFoundException;
import com.springboot.blog.models.Category;
import com.springboot.blog.repositories.CategoryRepo;
import com.springboot.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepo.save(category);

        return this.modelMapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        Category cat = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
        cat.setName(categoryDto.getName());
        cat.setDescription(categoryDto.getDescription());
        Category savedCat = categoryRepo.save(cat);
        return this.modelMapper.map(savedCat,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category cat = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
        categoryRepo.delete(cat);

    }

    @Override
    public CategoryDto getSingleCategory(Long categoryId) {
        Category cat = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
        return this.modelMapper.map(cat,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> allCategories() {
        List<Category> list = categoryRepo.findAll();
        return list.stream().map((cat)-> this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());

    }

}
