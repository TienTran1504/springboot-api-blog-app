package com.springboot.blog.service;

import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);
    CategoryDto getCategoryById(Long id);

    CategoryResponse getAllCategories(int pageNo, int pageSize, String sortBy, String sortDir);

    CategoryDto updateCategory(Long id, CategoryDto categoryDto);

    void deleteCategory(Long id);
}
