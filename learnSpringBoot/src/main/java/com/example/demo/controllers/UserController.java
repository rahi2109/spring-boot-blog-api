package com.example.demo.controllers;

import java.util.List;
import java.util.Map;

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

import com.example.demo.payloads.ApiResponce;
import com.example.demo.payloads.UserDto;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	//POST - create USER
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userdto){
		
		UserDto createUserDto=userService.createUser(userdto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	
	//PUT - Update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userdto,@PathVariable("userId") Integer userid){
		
		UserDto updatedUser=this.userService.updateUser(userdto, userid);
		return new ResponseEntity<UserDto> (updatedUser,HttpStatus.OK);
	}
	
	//DELETE - delete a user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponce> deleteUser(@PathVariable("userId") Integer uid){
		this.userService.deleteUser(uid);
	
	
		return new ResponseEntity<ApiResponce>(new ApiResponce("User deleted successfully",Boolean.TRUE),HttpStatus.OK);
		
	}
	
	// GET - 
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("userId")Integer userid){
		UserDto userdto=this.userService.getUserById(userid);
		return new ResponseEntity<UserDto>(userdto,HttpStatus.OK);
		
	}
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser(){
		List<UserDto>userList=this.userService.getAllUsers();
		return ResponseEntity.ok(userList);
	}
}
