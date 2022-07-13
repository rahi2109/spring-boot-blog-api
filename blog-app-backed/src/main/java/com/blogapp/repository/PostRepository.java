package com.blogapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blogapp.models.Category;
import com.blogapp.models.Post;
import com.blogapp.models.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	@Query(value = "From Post where title like : key")
	List<Post> seachByTitle(@Param("key")String keyword);
}
