package com.rabo.login.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	String login() {
		return "Inside the login";
	}
}
