package com.blogapp.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.models.Category;
import com.blogapp.payloads.CategoryDto;
import com.blogapp.repository.CategoryRepository;
import com.blogapp.services.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepo;

	@Override
	public CategoryDto createCategeory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		
		Category category=this.categoryDtoToCategory(categoryDto);
		

		
		Category insertedCategory=this.categoryRepo.save(category);
		
		return this.categoryToCategoryDto(insertedCategory);
	}

	@Override
	public CategoryDto updateCategeory(CategoryDto categoryDto, Integer id) {
		// TODO Auto-generated method stub
		Category category=this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", id));
		category.setTitle(categoryDto.getTitle());
		category.setDescription(categoryDto.getDescription());
		CategoryDto result=categoryToCategoryDto(this.categoryRepo.save(category));
		return result;
	}

	@Override
	public CategoryDto getCategeorybyId(Integer id) {
		// TODO Auto-generated method stub
		Category category=this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category ", "Category Id", id));
		
		return categoryToCategoryDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategeory() {
		// TODO Auto-generated method stub
		List<Category>categories=this.categoryRepo.findAll();
		List<CategoryDto>catdtos=categories.stream().map(category->categoryToCategoryDto(category)).collect(Collectors.toList());
		return catdtos;
	}

	@Override
	public void deleteCategeory(Integer id) {
		// TODO Auto-generated method stub
		Category category=this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category ", "Category Id", id));
		this.categoryRepo.delete(category);
		
	}
	
	private Category categoryDtoToCategory(CategoryDto categoryDto) {
		Category category=new Category();
		category.setTitle(categoryDto.getTitle());
		category.setDescription(categoryDto.getDescription());

		return category;
	}
	public CategoryDto categoryToCategoryDto(Category category) {
		CategoryDto categoryDto=new CategoryDto();
		categoryDto.setTitle(category.getTitle());
		categoryDto.setDescription(category.getDescription());
		return categoryDto;
	}

}
