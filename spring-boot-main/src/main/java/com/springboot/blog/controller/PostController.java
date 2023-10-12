package com.springboot.blog.controller;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.util.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

import java.util.List;

@Controller
@RequestMapping("/api/posts")
@Tag(
        name = "CRUD Rest API for Post Resources"
)
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //    create blogpost rest api
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @Operation(
            summary = "Create Post Rest API",
            description = "Create Post Rest API is used to save data to the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 Created"
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    //    get all posts
    @GetMapping
    @Operation(
            summary = "Get Post Rest API",
            description = "Get Post Rest API is used to get all the post in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NO, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir
    ) {
        PostResponse postResponse=postService.getAllPost(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    //    get post by id
    @Operation(
            summary = "Get Post By Id Rest API",
            description = "Get Post Rest API is used to get or read the specific post from the database by post Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }
//    @GetMapping("/{id}")
//    public String getPostById(@PathVariable(name = "id") long id,Model model) {
//        PostDto postDto=postService.getPostById(id);
//        model.addAttribute("title",postDto.getTitle());
//        model.addAttribute("description",postDto.getDescription());
//        model.addAttribute("content",postDto.getContent());
//        model.addAttribute("comment",postDto.getComments());
//        return "post";
//    }


    @Operation(
            summary = "Get Post By Category Id Rest API",
            description = "Get Post by Category Id Rest API is used to get or read the specific list of post from the database by its category Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable(value = "categoryId") long categoryId) {
        List<PostDto> postDtos = postService.getPostByCategory(categoryId);
        return ResponseEntity.ok(postDtos);
    }

    @Operation(
            summary = "Update Post By Id Rest API",
            description = "Get Update Rest API is used to change or update the specific post from the database by post Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") long id) {
        PostDto postResponse = postService.updatePost(postDto, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete Post By Id Rest API",
            description = "Get Delete Rest API is used to delete the specific post from the database by post Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
        postService.deletePost(id);
        return new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);
    }

    @Operation(
            summary = "Get Post By Search Rest API",
            description = "Get Post by Search Rest API is used to get or read the specific list of post from the database by the search keywords"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @GetMapping("/search")
    public ResponseEntity<List<PostDto>> searchPost(@RequestParam("query") String query){
        return ResponseEntity.ok(postService.searchPost(query));
    }

}
