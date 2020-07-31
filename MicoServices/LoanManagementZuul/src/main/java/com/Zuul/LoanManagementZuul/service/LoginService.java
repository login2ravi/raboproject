package com.Zuul.LoanManagementZuul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Zuul.LoanManagementZuul.entity.User;
import com.Zuul.LoanManagementZuul.model.LoginRequest;
import com.Zuul.LoanManagementZuul.repo.LoginRepo;
import com.Zuul.LoanManagementZuul.security.Authorizer;

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
