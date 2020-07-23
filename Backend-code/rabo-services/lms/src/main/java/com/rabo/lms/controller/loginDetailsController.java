package com.rabo.lms.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rabo.lms.entity.User;
import com.rabo.lms.model.LoginRequest;
import com.rabo.lms.service.LoginService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class loginDetailsController {
	
	
	 Logger logger = LoggerFactory.getLogger(loginDetailsController.class);
			 
			 
	@Autowired
	LoginService loginService;
	
	  
	@GetMapping("/admin")
	public String admin() {
		return "Hello World Testing";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	Map<String,String> validateUser(@RequestBody LoginRequest loginRequest) {
        
		System.out.println("username=="+loginRequest.getUserName());
		System.out.println("password=="+loginRequest.getPassword());
		if(loginService==null) {
			System.out.println("login service is null");
		}
        String token= loginService.loginUser(loginRequest);
        System.out.println("*****Token="+token);
        logger.debug("Token =="+token);
//        if(user == null) {
//        	System.out.println("User is null");
//        }
//        System.out.println("user--username=="+user.getUserName());
//		System.out.println("user--password=="+user.getPassword());
//		System.out.println("user--role=="+user.getUserRole());
		
		
//        ObjectMapper mapper = new ObjectMapper();
//        //Converting the Object to JSONString
//        String jsonString=null;
//        
//		try {
//			jsonString = mapper.writeValueAsString(user);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
        Map<String,String> resultMap = new HashMap<String,String>();
        if(token != null) {
        	User user = loginService.getUserDetails(loginRequest);
        	resultMap.put("username", loginRequest.getUserName());
    		resultMap.put("userrole", user.getUserRole());
    		resultMap.put("token", token);
        }
		
        return resultMap;
    }
	
	
	
}
