package com.blogapp.payloads;

import java.util.Date;

import com.blogapp.models.Category;
import com.blogapp.models.User;

public class PostDto {

//	Integer id;
	private String title;
	private String content;
	private String imgUrl;
	private Date date;
	private UserDto user;
	private CategoryDto category;

	public PostDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostDto(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}
	
}
