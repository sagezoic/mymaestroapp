package com.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.filters.JWTRequestFilter;
//Entry point of spring sec configuration
@EnableWebSecurity //to enable web security frmwork
@Configuration //to tell SC following is java configuration class : to declare spring beans
//Equivalent to bean config xml file, This class can contain bean declaration : @Bean
//annotated methods(equivalent to <bean id , class....../>
@EnableGlobalMethodSecurity(prePostEnabled = true) //to enable method level security , with pre auth n post auth
public class SecurityConfig {
	//dep : JWT filter
	@Autowired
	private JWTRequestFilter jwtFilter;
	
	//configures spring security for authorization (role based)
	@Bean
	public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception
	{
		http.csrf().disable(). //disable CSRF  to continue with REST APIs
		authorizeRequests() //specify all authorization rules (i.e authorize all requests)
		.antMatchers("/products/view","/login","/signup","/service","/service/edit","/service/delete","/swagger-ui/index.html").permitAll() // for incoming req ending with /products/view : no authentication n authorization needed
		.antMatchers("/products/purchase").hasRole("EXPLORER")//only explorer can purchase the products
		.antMatchers("/products/add").hasRole("MAESTRO") //only maestro can add the products
		.anyRequest().authenticated() //all remaining end points accessible only to authenticated users
		.and()
		.sessionManagement() //configure HttpSession management
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS) //DO NOT use HttpSession for storing any sec info
		.and()
		.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	//expose spring supplied auth mgr as a spring bean , so that auth controller can use it for authentication .
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}