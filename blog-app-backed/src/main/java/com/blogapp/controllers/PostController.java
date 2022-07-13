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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.configurations.AppConstants;
import com.blogapp.payloads.ApiResponse;
import com.blogapp.payloads.CategoryDto;
import com.blogapp.payloads.PostDto;
import com.blogapp.payloads.PostResponse;
import com.blogapp.serviceImpl.PostServiceImpl;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	PostServiceImpl postService;

	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId ){
		PostDto newPostDto=this.postService.create(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(newPostDto,HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{userId}/post")
	public ResponseEntity<List<PostDto>> getPostByUserId(@PathVariable Integer userId) {
		List<PostDto>postDtos=this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	@GetMapping("/category/{categoryId}/post")
	public ResponseEntity<List<PostDto>> getPostByCategoryId(@PathVariable Integer categoryId) {
		List<PostDto>postDtos=this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
	}
	
	@GetMapping("/post")
	public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber",defaultValue = AppConstants.pageNumber,required = false)Integer pageNumber,@RequestParam(value = "pageSize",defaultValue = AppConstants.pageSize,required = false)Integer pageSize ){
		PostResponse postResponse=this.postService.getAllPost(pageNumber, pageSize);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		PostDto postDto=this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/post/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponse("Post is Successfullt deleted",true);
	}
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId) {
		PostDto updatedPost=this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
	}
	public ResponseEntity<List<PostDto>> searchByTitle(@PathVariable String keyword){
		List<PostDto>postdtos=this.postService.seachByTitle(keyword);
		return new ResponseEntity<List<PostDto>>(postdtos,HttpStatus.OK);
	}
	
}
