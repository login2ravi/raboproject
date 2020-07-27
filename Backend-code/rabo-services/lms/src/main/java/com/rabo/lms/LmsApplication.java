package com.rabo.lms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabo.lms.security.SecurityFilter;;

@SpringBootApplication
@ComponentScan
@EntityScan("com.rabo.lms.entity")
@EnableJpaRepositories("com.rabo.lms.repo")
@EnableAsync
public class LmsApplication {

	
	@Autowired
	SecurityFilter securityFilter;
	
	public static void main(String[] args) {
		SpringApplication.run(LmsApplication.class, args);
	}
	
	@Bean
	FilterRegistrationBean filterRegistrationBean() {
		
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(securityFilter );
		registrationBean.addUrlPatterns("/secure/*");

		return registrationBean;
	}
	
	@Bean
	ObjectMapper objectMapper(){
		return  new ObjectMapper();
	}
	
//	@Bean
//	public WebMvcConfigurer corsConfig() {
//	
//		return new WebMvcConfigurerAdapter() {
//			@Override
//			public void addCorsMappings(CorsRegistry corsRegistry) {
//				corsRegistry.addMapping("/**").allowedOrigins("http://localhost:4200");
//				
//			}
//		};
//	}
	
	

}
