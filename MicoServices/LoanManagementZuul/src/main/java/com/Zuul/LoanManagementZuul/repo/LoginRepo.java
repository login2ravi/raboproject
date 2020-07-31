package com.Zuul.LoanManagementZuul.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Zuul.LoanManagementZuul.entity.User;

public interface LoginRepo extends JpaRepository<User, Integer>{
	
	User findByUserName(String userName);
	

}
