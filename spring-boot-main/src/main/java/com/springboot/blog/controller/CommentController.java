package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/posts")
@Tag(
        name = "CRUD Rest API for Comment Resources"
)
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(
            summary = "Create Comment By Post Id Rest API",
            description = "Create Rest API is used to create comment for a post by post Id"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 Created"
    )
    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                    @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }


    @Operation(
            summary = "Get Comment by Post By Id Rest API",
            description = "Get Comment by Post By Id Rest API is used to get the List of comment in a  post from the database by post Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") long postId) {
        return commentService.getCommandByPostId(postId);
    }

    @Operation(
            summary = "Get Comment by Comment Id and Post By Id Rest API",
            description = "Get Comment by Comment Id and Post By Id Rest API is used to get or read the specific comment in a  post from the database by post Id and comment Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") long postId, @PathVariable(value = "id") long commendId) {
        CommentDto commentDto = commentService.getCommentById(postId, commendId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Update Comment by Comment Id and Post By Id Rest API",
            description = "Update Comment by Comment Id and Post By Id Rest API is used to Update the specific comment in a  post from the database by post Id and comment Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @PutMapping("/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable(value = "postId") long postId,
            @PathVariable(value = "id") long commentId,
            @Valid @RequestBody CommentDto commentDto) {
        CommentDto commentDtoUpdated = commentService.updateComment(postId, commentId, commentDto);
        return ResponseEntity.ok(commentDtoUpdated);
    }

    @Operation(
            summary = "Delete Comment By Post Id and Comment Id Rest API",
            description = "Delete Comment by Post Id and Comment Id Rest API is used to delete the specific comment in a  post from the database by post Id and comment Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable long postId, @PathVariable long commentId) {
        commentService.deleteComment(postId, commentId);
        return ResponseEntity.ok("Comment is deleted successfully");
    }
}
