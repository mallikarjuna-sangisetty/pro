package com.project.traffic.service;

import java.util.List;

import com.project.traffic.model.User;

public interface UserService {

	void save(User user);
	boolean validateLogin(String username, String password);
	boolean findByUsername(String username);
	User findByLogin(String username);
	List<User> findAll();
	void update(User user);
	User findById(int id);

}
