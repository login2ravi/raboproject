package com.rabo.lms.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.rabo.lms.exception.*;


@Component
public class JwtAuthorizer implements Authorizer{

	
	//@Value ()
	//Integer expiryInSeconds;

    //@Value('${jwt.token.secret}')
    //String jwtSecret;
	
	@Override
	public Claims  validateToken(String jwtToken) {
		// TODO Auto-generated method stub
		try {
            String token = jwtToken.replaceAll("Bearer ", "");
            Claims claims = Jwts.parser().setSigningKey("rabo").parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception ex) {
        	 throw new BusinessException (ErrorCode.INVALID_HEADER, "Invalid Authorization header", ex);
        }
	}

	@Override
	public String generateToken(String userName) {
		// TODO Auto-generated method stub
		 String jwtToken = Jwts.builder().setSubject(userName)
	                .claim("roles", "user")
	                .setIssuedAt(new Date())
	                .signWith(SignatureAlgorithm.HS256, "rabo")
	                .setExpiration(new Date(System.currentTimeMillis() + 1200 * 1000))
	                .compact();
	        return jwtToken;
	}

	
	
}
