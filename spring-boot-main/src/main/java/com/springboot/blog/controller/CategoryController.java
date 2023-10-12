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
@RequestMapping("/api/categories")
@Tag(
        name = "CRUD Rest API for Category Resources"
)
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Create Category Rest API",
            description = "Create Category Rest API is used to save a category to the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 Created"
    )
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto newCategoryDto = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(newCategoryDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get Category By Id Rest API",
            description = "Get Category Rest API is used to get or read the specific Category from the database by Category Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable(value = "id") Long id) {
        CategoryDto categoryDto = categoryService.getCategory(id);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }


    @GetMapping
    @Operation(
            summary = "Get Category Rest API",
            description = "Get Category Rest API is used to get all the Category in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    public ResponseEntity<List<CategoryDto>> getCategories() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @Operation(
            summary = "Update Category By Id Rest API",
            description = "Update Category by Id Rest API is used to change or update the specific Category from the database by Category Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable(value = "id") Long categoryId, @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryDto, categoryId));
    }

    @Operation(
            summary = "Delete Category By Id Rest API",
            description = "Delete Category Rest API is used to delete the specific Category from the database by Category Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteCategory(@PathVariable(value = "id") Long categoryID) {
        categoryService.deleteCategory(categoryID);
        return ResponseEntity.ok("Category Deleted successfully");
    }
}
