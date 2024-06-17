package com.blogs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogs.dto.ApiResponse;
import com.blogs.entities.Category;
import com.blogs.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	public CategoryController() {
		System.out.println("in const of " + getClass());
	}

	@GetMapping
	public List<Category> listAllCategory() {
		System.out.println("in list All Category!!!");
		return categoryService.getAllCategories();
	}
	
	@PostMapping
	public Category addNewCategory(@RequestBody Category category)
	{
		System.out.println("in add new Category!!!!"+category);
		return categoryService.addNewCategory(category);
		
	}
	
	@DeleteMapping("/{categoryId}")
	//@pathVariable  method lvl argument to binding incoming path method var to method argument
	public ApiResponse deleteCategoryDetails(@PathVariable Long categoryId)
	{
		System.out.println("in delete CategoryDetails"+categoryId);
		return new ApiResponse
				(categoryService.deleteCategoryDetails(categoryId));
		
	}
	
	
	@GetMapping("/{catId}")
	public Category getCategoryDetails(@PathVariable Long catId)
	{
		System.out.println("in Category by "+catId);
		return categoryService.getCategoryDetails(catId);
		
	}
@PostMapping
	public Category updateCategoryDetails(@RequestBody Category category)
	{
		System.out.println("in update "+category);
		return categoryService.updateCategoryDetails(category);
	}
}
