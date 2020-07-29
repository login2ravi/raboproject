package com.Zuul.LoanManagementZuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class LoanManagementZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanManagementZuulApplication.class, args);
	}

}
