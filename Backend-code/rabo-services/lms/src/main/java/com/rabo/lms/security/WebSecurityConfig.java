package com.rabo.lms.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity //(debug = true) // when you want to see what filters are applied
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests()
        .antMatchers("/css/**", "/js/**", "/images/**", "/static/**", "/**/favicon.ico").permitAll()
        .antMatchers(HttpMethod.POST, "/login").permitAll()
        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
        //.antMatchers(HttpMethod.GET, "v2/api-docs").permitAll()
        //.antMatchers(HttpMethod.GET, "/v2/**").permitAll()
        //.antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
        .antMatchers(
                HttpMethod.GET,
                "/",
                "/v2/api-docs",           // swagger
                "/v3/api-docs",           // swagger
                "/webjars/**",            // swagger-ui webjars
                "/swagger-resources/**",  // swagger-ui resources
                "/configuration/**",      // swagger configuration
                "/*.html",
                "/favicon.ico",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js"
        ).permitAll()
        
        
				/*
				 * .antMatchers(HttpMethod.OPTIONS, "/login").permitAll()
				 * .antMatchers(HttpMethod.OPTIONS, "/search").permitAll()
				 * .antMatchers(HttpMethod.OPTIONS, "/updateloan").permitAll()
				 * .antMatchers(HttpMethod.OPTIONS, "/addloan").permitAll()
				 * .antMatchers(HttpMethod.OPTIONS, "/getloandetails/**").permitAll()
				 */
        .antMatchers("/secure/**").permitAll()
        .antMatchers("/").permitAll()
        .anyRequest().authenticated()
        .and().httpBasic();
  }
}