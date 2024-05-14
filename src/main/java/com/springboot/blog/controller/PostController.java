package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@Tag(
        name = "Post Resource"
)
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // createPost method here
    @Operation(
            summary = "Create a new post",
            description = "Create a new post into the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Post created successfully"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    //get all posts rest api
    @Operation(
            summary = "Get all posts",
            description = "Get all posts from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "All posts retrieved successfully"
    )
    @GetMapping
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = AppConstants.DEFAULT_SORT_ORDER, required = false) String sortOrder
    ) {
        return ResponseEntity.ok(postService.getAllPosts(pageNo,pageSize,sortBy,sortOrder));
    }

    //get post by id rest api
    @Operation(
            summary = "Get post by id",
            description = "Get post by id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Post retrieved successfully"
    )
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name="id") Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    //update post rest api
    @Operation(
            summary = "Update post",
            description = "Update post in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Post updated successfully"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name="id") Long id) {
        return ResponseEntity.ok(postService.updatePost(postDto, id));
    }

    //delete post rest api
    @Operation(
            summary = "Delete post",
            description = "Delete post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Post deleted successfully"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name="id") Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>("Post " + id + " deleted successfully",HttpStatus.OK);
    }

    //get posts by category id rest api
    @Operation(
            summary = "Get posts by category id",
            description = "Get posts by category id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Posts retrieved successfully"
    )
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostsByCategoryId(@PathVariable(name="categoryId") Long categoryId) {
        return ResponseEntity.ok(postService.getPostsByCategoryId(categoryId));
    }
}
