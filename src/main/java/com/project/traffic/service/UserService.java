package com.project.traffic.service;

import com.project.traffic.model.User;

public interface UserService {

	void save(User user);
	boolean validateLogin(String username, String password);
	boolean findByUsername(String username);
	User findByLogin(String username);

}
