package com.rabo.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RestController;

import com.rabo.lms.model.LoginRequest;
import com.rabo.lms.service.LoginService;

@RestController
public class loginDetailsController {
	@Autowired
	LoginService loginService;
	
	  
	@GetMapping("/admin")
	public String admin() {
		return "Hello World Testing";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	String validateUser(@RequestBody LoginRequest loginRequest) {
        
		System.out.println("username=="+loginRequest.getUserName());
		System.out.println("password=="+loginRequest.getPassword());
		if(loginService==null) {
			System.out.println("login service is null");
		}
        String token= loginService.loginUser(loginRequest);
        System.out.println("*****Token="+token);
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
        return token;
    }
	
	
	
}
