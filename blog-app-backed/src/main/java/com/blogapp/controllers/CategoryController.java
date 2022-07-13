package com.blogapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.payloads.ApiResponse;
import com.blogapp.payloads.CategoryDto;
import com.blogapp.serviceImpl.CategoryServiceImpl;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	CategoryServiceImpl catseerviceImpl;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
		CategoryDto newCategory=this.catseerviceImpl.createCategeory(categoryDto);
//		System.out.println("Title "+newCategory.getTitle());
		return  new ResponseEntity<CategoryDto>(newCategory,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@PathVariable ("id") Integer cat_id,@RequestBody CategoryDto categoryDto){
		
		CategoryDto updatedCategory=this.catseerviceImpl.updateCategeory(categoryDto, cat_id);
		return ResponseEntity.ok(updatedCategory);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id){
		
		CategoryDto catDto=this.catseerviceImpl.getCategeorybyId(id);
		return new ResponseEntity<CategoryDto>(catDto,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto>catDtoList=this.catseerviceImpl.getAllCategeory();
		return new ResponseEntity<List<CategoryDto>>(catDtoList,HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id){
		this.catseerviceImpl.deleteCategeory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully ", true),HttpStatus.OK);
	}

}
