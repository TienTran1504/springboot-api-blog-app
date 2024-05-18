package com.springboot.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private long id;
    //name should not be null or empty and at least 2 characters
//    @NotEmpty(message = "Name is required")

    private String name;

    // email should not be null or empty and should be a valid email
//    @NotEmpty(message = "Email is required")
//    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty
    @Size(min = 10, message = "Body should be at least 10 characters")
    private String body;
}
