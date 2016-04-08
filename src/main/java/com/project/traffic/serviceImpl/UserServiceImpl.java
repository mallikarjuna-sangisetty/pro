package com.project.traffic.serviceImpl;

import java.util.List;

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

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }
   
    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }
}
