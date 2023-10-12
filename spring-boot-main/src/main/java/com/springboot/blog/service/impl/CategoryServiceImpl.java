package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Category;
import com.springboot.blog.exception.BlogApiException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository repository;
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category=modelMapper.map(categoryDto, Category.class);
        Category newCategory=repository.save(category);
        return modelMapper.map(newCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(Long categoryId) {
        Category category=repository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories=repository.findAll();
        return categories.stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        Category category=repository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
//        category.setId(categoryDto.getId());
        Category updatedCategory=repository.save(category);
        return modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category=repository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));
        repository.delete(category);
    }


}
