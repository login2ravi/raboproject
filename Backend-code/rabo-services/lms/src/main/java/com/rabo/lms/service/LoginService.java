package com.rabo.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabo.lms.entity.User;
import com.rabo.lms.model.LoginRequest;
import com.rabo.lms.repo.LoginRepo;
import com.rabo.lms.security.Authorizer;

@Service
public class LoginService {
	
	@Autowired
	private LoginRepo loginRepo;
	
	@Autowired
	  Authorizer authorizer;
	
	public String loginUser(LoginRequest loginRequest) {
		
		User user = loginRepo.findByUserName(loginRequest.getUserName());
		
		if(user != null && user.getPassword().equals(loginRequest.getPassword())) { 
			return authorizer.generateToken(loginRequest.getUserName());	
		}
		return null;
	}
	
	
	public User getUserDetails(LoginRequest loginRequest) {
		
		User user = loginRepo.findByUserName(loginRequest.getUserName());
		
		if(user != null && user.getPassword().equals(loginRequest.getPassword())) { 
			return user;	
		}
		return null;
	}

}
