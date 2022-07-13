package com.blogapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapp.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
