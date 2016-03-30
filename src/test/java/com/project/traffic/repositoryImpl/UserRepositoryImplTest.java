package com.project.traffic.repositoryImpl;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.project.traffic.model.User;
import com.project.traffic.repository.UserRepository;
import com.project.traffic.uiImpl.UserProfileHandler;

public class UserRepositoryImplTest{
	
	static UserRepository repository;
	static UserProfileHandler userProfileHandler;
	@BeforeClass
	public static void setup(){
		repository = new UserRepositoryImpl();
		userProfileHandler = new UserProfileHandler();
	}
	@Test
	public void getAll(){
		List<User> list = repository.findAll();
		for (User user : list) {
			System.out.println(user.getTickets());
		}
		userProfileHandler.displayUser(list);
	}


}
