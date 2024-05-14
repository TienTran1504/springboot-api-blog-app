package com.springboot.blog.payload;

import jakarta.validation.constraints.Email;
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
public class RegisterDto {
    @NotEmpty(message = "Name is required")
    @Size(min = 3, message = "Name should be at least 3 characters")
    private String name;
    @NotEmpty(message = "Username is required")
    @Size(min = 3, message = "Username should be at least 3 characters")
    private String username;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 4, message = "Password should be at least 4 characters")
    private String password;
}
