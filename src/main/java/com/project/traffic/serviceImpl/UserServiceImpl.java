package com.project.traffic.serviceImpl;

import com.project.traffic.model.User;
import com.project.traffic.repository.UserRepository;
import com.project.traffic.repositoryImpl.UserRepositoryImpl;
import com.project.traffic.service.UserService;

public class UserServiceImpl implements UserService {

	private static UserRepository userRepository;
	static{
		userRepository = new UserRepositoryImpl();
	}
	@Override
	public void save(User user) {
		userRepository.save(user);
	}
	@Override
	public boolean validateLogin(String username, String password) {
		return userRepository.validateLogin(username,password);
	}
	@Override
	public boolean findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	@Override
	public User findByLogin(String username) {
		return userRepository.findByLogin(username);
	}

}
