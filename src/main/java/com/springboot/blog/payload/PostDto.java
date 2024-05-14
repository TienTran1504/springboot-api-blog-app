package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Schema(
        name = "Post",
        description = "PostDto Model Information"
)
public class PostDto {
    private long id;
    //title should not be null or empty and at least 2 characters
    @Schema(description = "Post title")
    @NotEmpty(message = "Title is required")
    @Size(min = 2, message = "Title should be at least 2 characters")

    private String title;

    // post description should be not null or empty and at least 10 characters
    @Schema(
            description = "Post description"
    )
    @NotEmpty(message = "Description is required")
    @Size(min = 10, message = "Description should be at least 10 characters")
    private String description;

    // post content should be not null or empty
    @Schema(
            description = "Post content"
    )
    @NotEmpty(message = "Content is required")
    private String content;

    private Set<CommentDto> comments;

    // post category id
    @Schema(
            description = "Post category id"
    )

    private Long categoryId;
}
