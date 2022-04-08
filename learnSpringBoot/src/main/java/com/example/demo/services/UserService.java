package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.payloads.UserDto;


public interface UserService {

	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer userid);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
	
}
