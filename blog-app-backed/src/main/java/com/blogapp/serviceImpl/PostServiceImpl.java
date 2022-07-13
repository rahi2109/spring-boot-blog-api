package com.blogapp.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.models.Category;
import com.blogapp.models.Post;
import com.blogapp.models.User;
import com.blogapp.payloads.PostDto;
import com.blogapp.payloads.PostResponse;
import com.blogapp.repository.CategoryRepository;
import com.blogapp.repository.PostRepository;
import com.blogapp.repository.UserRepository;
import com.blogapp.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepo;
	@Autowired
	CategoryRepository categoryRepo;
	@Autowired
	UserRepository userRepo;
	//just for model mapping
	@Autowired
	UserServiceImpl userService;
	@Autowired
	CategoryServiceImpl categoryService;
	
	
	@Override
	public PostDto create(PostDto postDto, Integer userId,Integer CategoryId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User ", "user Id", userId));
		Category category=this.categoryRepo.findById(CategoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", CategoryId));
		Post post=PostDtoToPost(postDto);
		post.setImgUrl("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost=this.postRepo.save(post);
		
	
		
		return this.PostToPostDto(newPost);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		Post updatedPost=this.postRepo.save(post);
		return this.PostToPostDto(updatedPost);
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
		postRepo.delete(post);
		
		
	}

	@Override
	public PostResponse getAllPost(Integer PageNumber,Integer PageSize) {
		// TODO Auto-generated method stub
		int pageSize=5;
		int pageNumber=1;
		Pageable page=PageRequest.of(pageNumber, pageSize);
		Page<Post>pagePosts=this.postRepo.findAll(page);
		List<Post>posts=pagePosts.getContent();
		List<PostDto>postDtos=posts.stream().map(post->this.PostToPostDto(post)).collect(Collectors.toList());
		PostResponse postRes=new PostResponse();
		postRes.setContent(postDtos);
		postRes.setPageNumber(pagePosts.getNumber());
		postRes.setPageSize(pagePosts.getSize());
		postRes.setTotalNumberOfPages(pagePosts.getTotalPages());
		postRes.setTotalElements(pagePosts.getTotalElements());
		postRes.setLastPage(pagePosts.isLast());
		return postRes;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		// TODO Auto-generated method stub
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post ", "Post Id", postId));
		return this.PostToPostDto(post);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(category);
		List<PostDto>postDtos=posts.stream().map(post->this.PostToPostDto(post)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User ", "user Id ", userId));
		List<Post>posts=this.postRepo.findByUser(user);
		List<PostDto>postDtos=posts.stream().map(post->this.PostToPostDto(post)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> seachByTitle(String searchString) {
		// TODO Auto-generated method stub
		List<Post>posts=this.postRepo.seachByTitle("%"+searchString+"%");
		List<PostDto>postdtos=posts.stream().map(post->this.PostToPostDto(post)).collect(Collectors.toList());
		return postdtos;
	}
	
	private Post PostDtoToPost(PostDto postDto) {
		Post post=new Post();
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		return post;
	}

	private PostDto PostToPostDto(Post post) {
		PostDto postDto=new PostDto();
		postDto.setTitle(post.getTitle());
		postDto.setContent(post.getContent());
		
		postDto.setCategory(this.categoryService.categoryToCategoryDto(post.getCategory()));
		postDto.setUser(this.userService.userToUserDto(post.getUser()));
		postDto.setImgUrl(post.getImgUrl());
		postDto.setDate(post.getAddedDate());
		return postDto;
	}
}