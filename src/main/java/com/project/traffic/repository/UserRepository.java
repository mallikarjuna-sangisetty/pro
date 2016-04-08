package com.project.traffic.repository;

import java.util.List;

import com.project.traffic.model.User;

public interface UserRepository {

	void save(User user);
	boolean findByUsername(String username);
	List<User> findAll();
	void update(User user);
	void deleteByUsername(String username);
	void deleteById(int id);
	boolean validateLogin(String username, String password);
	User findByLogin(String username);
	User findById(int id);

}
