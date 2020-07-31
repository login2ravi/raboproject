package com.Zuul.LoanManagementZuul.security;

import io.jsonwebtoken.Claims;

public interface Authorizer {
	
	Claims  validateToken(String jwtToken);
	    String generateToken(String email);

}
