package com.blogapp.services;

import java.util.List;

import com.blogapp.payloads.CategoryDto;

public interface CategoryService {

	public CategoryDto createCategeory(CategoryDto categoryDto);
	
	public CategoryDto updateCategeory(CategoryDto categoryDto,Integer id);
	
	public CategoryDto getCategeorybyId(Integer id);
	
	public List<CategoryDto> getAllCategeory();
	
	public void deleteCategeory(Integer id);
}
