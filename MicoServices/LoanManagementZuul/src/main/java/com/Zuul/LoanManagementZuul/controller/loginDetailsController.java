package com.Zuul.LoanManagementZuul.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Zuul.LoanManagementZuul.entity.User;
import com.Zuul.LoanManagementZuul.model.LoginRequest;
import com.Zuul.LoanManagementZuul.service.LoginService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class loginDetailsController {
	
	
	 Logger logger = LoggerFactory.getLogger(loginDetailsController.class);
			 
			 
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	Map<String,String> validateUser(@RequestBody LoginRequest loginRequest) {
		HttpHeaders headers = new HttpHeaders();
		
        logger.info("ZUULInside the validate user");
        String token= loginService.loginUser(loginRequest);
        logger.info("ZZUL Token =="+token);
        
        Map<String,String> resultMap = new HashMap<String,String>();
        if(token != null) {
        	User user = loginService.getUserDetails(loginRequest);
        	resultMap.put("username", loginRequest.getUserName());
    		resultMap.put("userrole", user.getUserRole());
    		resultMap.put("token", token);
    		headers.add("Access-Control-Expose-Headers", "Access-Control-Allow-Origin,Authorization");
    		headers.add("Authorization", "bearer " + token);
        }
		
        return resultMap;
    }
}
