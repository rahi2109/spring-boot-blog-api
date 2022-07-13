package com.blogapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;

import com.blogapp.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
