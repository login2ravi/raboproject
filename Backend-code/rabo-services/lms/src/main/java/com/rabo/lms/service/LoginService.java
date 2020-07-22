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
		System.out.println("Inside service ");
		User user = loginRepo.findByUserName(loginRequest.getUserName());
		System.out.println("Inside service Ends");
		if(user != null) { 
			return authorizer.generateToken(loginRequest.getUserName());	
		}
		return null;
	}

}
