package com.project.traffic.controller;

import com.project.traffic.model.User;
import com.project.traffic.service.UserService;
import com.project.traffic.serviceImpl.UserServiceImpl;

public class UserController {

	private static UserService service;
	
	static{
		service = new UserServiceImpl();
	}
	
	public void saveUser(User user){
		service.save(user);
	}
	
	public boolean validateLogin(String username,String password){
		return service.validateLogin(username,password);
	}
	
	public boolean findByUsername(String username){
		return service.findByUsername(username);
	}
	
	public User findByLogin(String username){
		return service.findByLogin(username);
	}
}
