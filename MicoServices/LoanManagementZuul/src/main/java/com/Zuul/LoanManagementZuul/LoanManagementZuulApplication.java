package com.Zuul.LoanManagementZuul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.Zuul.LoanManagementZuul.security.SecurityFilter;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@EnableZuulProxy
@ComponentScan
@EntityScan("com.Zuul.LoanManagementZuul.entity")
@EnableJpaRepositories("com.Zuul.LoanManagementZuul.repo")
@EnableAsync
@EnableEurekaClient
@CrossOrigin(origins = "http://localhost:4200/*")
public class LoanManagementZuulApplication {
	

	@Autowired
	SecurityFilter securityFilter;

	public static void main(String[] args) {
		SpringApplication.run(LoanManagementZuulApplication.class, args);
	}
	
	
	  @Bean FilterRegistrationBean filterRegistrationBean() {
	  
	  final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
	  registrationBean.setFilter(securityFilter );
	  registrationBean.addUrlPatterns("/secure/*");
	  
	  return registrationBean; }
	 	
	@Bean
	ObjectMapper objectMapper(){
		return  new ObjectMapper();
	}


}
