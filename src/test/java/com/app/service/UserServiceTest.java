/*
 * package com.app.service;
 * 
 * import static org.junit.jupiter.api.Assertions.*;
 * 
 * import org.junit.jupiter.api.Test; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.context.SpringBootTest;
 * 
 * import com.app.dto.UserDto; import com.app.dto.UserSignupResponseDto; import
 * com.app.entities.UserRole;
 * 
 * @SpringBootTest // => SC will load all spring beans , including controller ,
 * service n dao class UserServiceTest { // dep : user service
 * 
 * @Autowired private UserService userService;
 * 
 * @Test void testAdminRegistration() { // firstName, String lastName, String
 * email, String password, UserRole role UserDto admin = new UserDto("Rama",
 * "Kadam", "rama@gmail.com", "12345", UserRole.ROLE_MAESTRO); //UserDto
 * registeredUser = userService.registerUser(admin);
 * //assertTrue(registeredUser.getId()==1); }
 * 
 * @Test void testCustomerRegistration() { // firstName, String lastName, String
 * email, String password, UserRole role UserDto customer = new UserDto("Mihir",
 * "Sen", "mihir@gmail.com", "2345", UserRole.ROLE_EXPLORER);
 * UserSignupResponseDto registeredUser = userService.registerUser(customer);
 * //assertTrue(registeredUser.getId()==2); }
 * 
 * }
 */