/**
 * 
 */
package com.rabo.lms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rabo.lms.entity.User;
import com.rabo.lms.model.LoginRequest;
import com.rabo.lms.repo.LoginRepo;
import com.rabo.lms.security.Authorizer;

/**
 * @author ctsjava106
 *
 */

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {
	
	@InjectMocks
	private LoginService loginService;
	
	@Mock
	private LoginRepo loginRepo;
	
	@Mock
	Authorizer authorizer;
	
	@Test
	public void loginUser() {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserName("admin");
		loginRequest.setPassword("admin123");
		String expectedToken = "Token1";
		
		User user = new User();
		user.setPassword("admin123");
		user.setUserName("admin");
		user.setUserRole("admin");
		
		Mockito.when(loginRepo.findByUserName(loginRequest.getUserName())).thenReturn(user);
		Mockito.when(authorizer.generateToken(loginRequest.getUserName())).thenReturn(expectedToken);
		
		String token = loginService.loginUser(loginRequest);
		assertEquals(token, expectedToken);
		
	}
	
	@Test
	public void loginUserNullPassword() {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserName("admin");
		loginRequest.setPassword(null);
		
		
		User user = new User();
		user.setPassword("admin123");
		user.setUserName("admin");
		user.setUserRole("admin");
		
		Mockito.when(loginRepo.findByUserName(loginRequest.getUserName())).thenReturn(user);
		
		String token = loginService.loginUser(loginRequest);
		assertEquals(token, null);
		
	}
	
	@Test
	public void loginUserInvalidPassword() {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserName("admin");
		loginRequest.setPassword("test");
		
		
		User user = new User();
		user.setPassword("admin123");
		user.setUserName("admin");
		user.setUserRole("admin");
		
		Mockito.when(loginRepo.findByUserName(loginRequest.getUserName())).thenReturn(user);
		
		String token = loginService.loginUser(loginRequest);
		assertEquals(token, null);
		
	}
	
	
	@Test
	public void getUserDetails() {
		
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserName("admin");
		loginRequest.setPassword("admin123");
		
		User user = new User();
		user.setPassword("admin123");
		user.setUserName("admin");
		user.setUserRole("admin");
		
		Mockito.when(loginRepo.findByUserName(loginRequest.getUserName())).thenReturn(user);
		
		User actual = loginService.getUserDetails(loginRequest);
		
		assertEquals(user, actual);
		
		
	}
	
	
	@Test
	public void getUserDetailsInvalidUserName() {
		
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserName(null);
		loginRequest.setPassword("admin123");
		
		
		User actual = loginService.getUserDetails(loginRequest);
		
		assertEquals(actual, null);
		
		
	}
	
	

}
