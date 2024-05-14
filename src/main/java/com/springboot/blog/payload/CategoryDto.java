package com.springboot.blog.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long id;
    @NotEmpty(message = "Category name is required")
    @Size(min = 3, message = "Category name should be at least 3 characters")
    private String name;

    @NotEmpty(message = "Category description is required")
    @Size(min = 8, message = "Category description should be at least 8 characters")
    private String description;
}
