package com.blogapp.services;

import java.util.List;

import com.blogapp.models.Post;
import com.blogapp.payloads.PostDto;
import com.blogapp.payloads.PostResponse;

public interface PostService {
	
	PostDto create(PostDto postDto,Integer userId,Integer CategoryId);
	PostDto updatePost(PostDto postDto,Integer postId);
	void deletePost(Integer postId);
	PostResponse getAllPost(Integer PageNumber,Integer PageSize);
	PostDto getPostById(Integer postId);
	List<PostDto> getPostByCategory(Integer CategoryId);
	List<PostDto>getPostByUser(Integer userId);
//	List<PostDto>searchPost(String searchString);
	List<PostDto> seachByTitle(String searchString);
	
}
