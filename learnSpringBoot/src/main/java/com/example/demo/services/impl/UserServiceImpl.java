package com.example.demo.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.Users;
import com.example.demo.payloads.UserDto;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	@Override
	public UserDto createUser(UserDto userdto) {
		// TODO Auto-generated method stub
		Users user=this.dtoToUser(userdto);
		Users savedUser=this.userRepository.save(user);
		return this.usersTodto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userdto, Integer userid) {
		// TODO Auto-generated method stub

		Users users=userRepository.findById(userid).orElseThrow((()->new ResourceNotFoundException("User", " id", userid)));
		users.setName(userdto.getName());
		users.setEmail(userdto.getEmail());
		users.setPassword(userdto.getPassword());
		users.setAbout(userdto.getAbout());
		Users updatedUser=userRepository.save(users);
		return this.usersTodto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		Users users=userRepository.findById(userId).orElseThrow((()->new ResourceNotFoundException("User", " id", userId)));
		
		return this.usersTodto(users);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<Users>usersList=this.userRepository.findAll();
		return usersList.stream().map(u->this.usersTodto(u)).collect(Collectors.toList());
		
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		System.out.println("Debug from Service before db call ");
		Users users=userRepository.findById(userId).orElseThrow((()->new ResourceNotFoundException("User", " id", userId)));
		System.out.println("Debug from Service after db call " + users);
		this.userRepository.delete(users);
		System.out.println("User deleted Successfylly");
		
		
	}
	
	private Users dtoToUser(UserDto userdto) {
		
		Users user=new Users();
		user.setId(userdto.getId());
		user.setName(userdto.getName());
		user.setAbout(userdto.getAbout());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		return user;
	}
private UserDto usersTodto(Users user) {
		
		UserDto userdto=new UserDto();
		userdto.setId(user.getId());
		userdto.setName(user.getName());
		userdto.setAbout(user.getAbout());
		userdto.setEmail(user.getEmail());
		userdto.setPassword(user.getPassword());
		return userdto;
	}

}
