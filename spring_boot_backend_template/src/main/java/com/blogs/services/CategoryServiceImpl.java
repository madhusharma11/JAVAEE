
package com.blogs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blogs.custom_excpetions.InvalidCredentialsException;
import com.blogs.entities.Category;
import com.blogs.repository.CategoryRepository;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category addNewCategory(Category newCategory) {
		// use the inherited method save() from CrudRepo
		return categoryRepository.save(newCategory);
	}

	@Override
	public String deleteCategoryDetails(Long catId) {
		// TODO Auto-generated method stub
		if (categoryRepository.existsById(catId)) {
			categoryRepository.deleteById(catId);
			return "Category details deleted!!!!";
		}

		return "Deleting Category details faild!!!...Invalid category id";
	}

	@Override
	public Category getCategoryDetails(Long catId) {
	Optional<Category> optional=
	categoryRepository.findById(catId);
	
//		return optional.orElseThrow(()->
//		new ResourceNotFoundException("Invalid Category Id!!!"));
//			
	return optional.orElseThrow(()->
	new InvalidCredentialsException("invalid Category id"));
		}
	
	

	@Override
	public Category updateCategoryDetails(Category newCategory) {
		
		return categoryRepository.save(newCategory);
	}

}
