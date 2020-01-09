package com.demo.config;

import org.springframework.context.annotation.Configuration;

@Configuration

public class ApplicationSecurity /*extends WebSecurityConfigurerAdapter */
{
	/*@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.csrf().disable();
		http.antMatcher("/**").authorizeRequests().
		antMatchers("/","/adminLogin","/register","/registerData","/display","/adminLogin","/delete","/update").
		permitAll();
		
		
		
			http.csrf().disable();
			http.antMatcher("/**").authorizeRequests()
			.antMatchers("/**" )
			.permitAll();
	}*/

}
