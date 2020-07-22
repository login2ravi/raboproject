package com.rabo.lms.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    Authorizer authorizer;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
    	System.out.println("Dofilter starting");
        final String jwtToken = httpServletRequest.getHeader("Authorization");
        
        try {
            if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
                Claims claims = authorizer.validateToken(jwtToken);

                if (isTokenExpired(claims)) {
                    //throw new BusinessException(ErrorCode.INVALID_TOKEN, "Authorization header is invalid");
                	throw new Exception("Authorization header is invalid");
                }

                httpServletRequest.setAttribute("claims", claims);
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } else {
            	System.out.println("Dofilter ELSEBlock.......");
                //throw new BusinessException(ErrorCode.INVALID_HEADER, "Authorization header is invalid/not found");
            	throw new Exception("Authorization header is invalid/not found");
            }
        } catch (Exception e) {
        	e.printStackTrace();
        	System.out.println("Dofilter Exception.......");
            //logger.error("Authorization header is invalid/not found", e);
            //ErrorResponse errorResponse = new ErrorResponse("Error code", "Error ");

            //httpServletResponse.setContentType("application/json");
            //httpServletResponse.setStatus(e.errorCode.httpStatus.value());
            //httpServletResponse.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        }
        System.out.println("Dofilter End.......");
    }

    private Boolean isTokenExpired(Claims claims) {
    	System.out.println("isTokenExpired starts.......");
        Date expirationDate = claims.getExpiration();
        Date currentDate = new Date();
        return expirationDate.before(currentDate);
    }
}