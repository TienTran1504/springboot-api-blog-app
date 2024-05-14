package com.springboot.blog.controller;

import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@Tag(name = "Category Resource")
public class CategoryController {
    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Add a new category API
    @Operation(
            summary = "Add a new category",
            description = "Add a new category into the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Category created successfully"
    )
    @PostMapping
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto savedCategory = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    // Get Category by Id
    @Operation(
            summary = "Get category by id",
            description = "Get category by id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Category retrieved successfully"
    )
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        CategoryDto categoryDto = categoryService.getCategoryById(id);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    //Get all categories API
    @Operation(
            summary = "Get all categories",
            description = "Get all categories from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "All categories retrieved successfully"
    )
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    // Update category API
    @Operation(
            summary = "Update a category",
            description = "Update a category in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Category updated successfully"
    )
    @PutMapping("/{id}")
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        CategoryDto updatedCategory = categoryService.updateCategory(id, categoryDto);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    //Delete category API
    @Operation(
            summary = "Delete a category",
            description = "Delete a category from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Category deleted successfully"
    )
    @DeleteMapping("/{id}")
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>("Category " + id + " deleted successfully", HttpStatus.OK);
    }
}
