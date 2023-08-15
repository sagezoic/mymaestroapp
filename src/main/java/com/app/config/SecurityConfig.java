package com.app.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.filters.JWTRequestFilter;

//heart of spring security configuration
//Central point of spring security configuration
//Entry point of spring security configuration

@EnableWebSecurity // to enable the web security framework 
@Configuration //to tell SC following is java config class: to declare spring beans
//Equivalent to bean configuration xml file, This class can contain the 
//bean declaration: @Bean annotated methods(equivalent to <bean id, class..../>
@EnableGlobalMethodSecurity(prePostEnabled = true) // to enable method level security and prepostEnable meand with preauthorization and post authorization
public class SecurityConfig {
	
	//dep:JWT filter
	
	@Autowired
	private JWTRequestFilter jwtFilter;
	
	
	
	
	
	
	
	
	//dep: password encoder
	
//	@Autowired
//	public PasswordEncoder encoder;
	
	//bean configured for authentication
//	@Bean 
//	public UserDetailsService users() {
//		//creating user object: username, pwd, role admin
//		User admin=new User("Rama",encoder.encode("12345"), List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
//		User customer=new User("Kiran",encoder.encode("2345"), List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER")));
//		
//		return new InMemoryUserDetailsManager(customer,admin);
//		//returns user details service:containing 2 users: admin and customer
//	}
	
	//configures spring security for authorization purpose(role based authorization)
//	@Bean
//	public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception
//	{
//		http.authorizeRequests() //specify all authorization rules(i.e authorize all requests)
//		.antMatchers("/products/view").permitAll() //for the incoming request ending with /products/view : no authentication and no authorization needed
//		.antMatchers("/products/purchase").hasRole("CUSTOMER") //only customer can purchase the products 
//		.antMatchers("/products/add").hasRole("ADMIN") // only admin can add the products
//		.anyRequest().authenticated() // all remaining endpoints can be accessible only to authenticated users
//		.and()
//		.httpBasic(); //configures http Basic authentication(using Base64 encoded username:pwd)
//		return http.build();
//	}
	
	@Bean
	public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception
	{
		http.csrf().disable().//disable CSRF to continue with REST APIs
		authorizeRequests() //specify all authorization rules(i.e authorize all requests)
		.antMatchers("/login").permitAll()
		//.antMatchers("/products/view","/users/signin","/user/signup").permitAll() //for the incoming request ending with /products/view : no authentication and no authorization needed
		//.antMatchers("/products/purchase").hasRole("CUSTOMER") //only customer can purchase the products 
		//.antMatchers("/products/add").hasRole("ADMIN") // only admin can add the products
		.anyRequest().authenticated() // all remaining endpoints can be accessible only to authenticated users
		.and()
		.sessionManagement()//configure HttpSession management
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS) //DO NOt use HttpSession for storing any sec info
		.and()
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	//expose spring supplied autn manager as a spring bean, so that auth controller can use it for authentication purpose
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
	{
		return config.getAuthenticationManager();
	}
	
}
