package com.rabo.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rabo.lms.entity.User;

public interface LoginRepo extends JpaRepository<User, Integer>{
	
	User findByUserName(String userName);
	

}
