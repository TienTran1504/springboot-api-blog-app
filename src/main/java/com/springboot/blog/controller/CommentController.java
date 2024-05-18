package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(
        name = "Comment Resource"
)
public class CommentController {
    private CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(
            summary = "Create a new comment",
            description = "Create a new comment into the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Comment created successfully"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(name = "postId") Long postId, @Valid @RequestBody CommentDto commentDto) {
//        //get info user logged in current user
//        commentDto.setName(User());
//        commentDto.setEmail(UserDetails.getUsername());
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get all comments",
            description = "Get all comments from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "All comments retrieved successfully"
    )
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable(name = "postId") Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }

    @Operation(
            summary = "Get comment by id",
            description = "Get comment by id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Comment retrieved successfully"
    )
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(name = "postId") Long postId, @PathVariable(name = "commentId") Long commentId) {
        return ResponseEntity.ok(commentService.getCommentById(postId, commentId));
    }

    @Operation(
            summary = "Update a comment",
            description = "Update a comment into the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Comment updated successfully"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(name = "postId") Long postId, @PathVariable(name = "commentId") Long commentId, @Valid @RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(commentService.updateComment(postId, commentId, commentDto));
    }

    @Operation(
            summary = "Delete a comment",
            description = "Delete a comment from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Comment deleted successfully"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable(name = "postId") Long postId, @PathVariable(name = "commentId") Long commentId) {
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment " + commentId + " of Post " + postId +" deleted successfully", HttpStatus.OK);
    }
}
