//package com.student.app.jwt.token.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.student.app.jwt.token.error.UserNotFoundException;
//import com.student.app.jwt.token.jwt.JWTUtility;
//import com.student.app.jwt.token.jwt.JwtResponse;
//import com.student.app.jwt.token.model.Login;
//import com.student.app.jwt.token.model.User;
//import com.student.app.jwt.token.model.UserService;
//
//@RestController
//@RequestMapping("/api/student")
//public class TokenController {
//
//	private static final Logger logger = LoggerFactory.getLogger(TokenController.class);
//
//	private UserService userService;
//	private JWTUtility jWTUtility;
//	private JwtResponse jwtResponse;
//
//	public TokenController(UserService userService, JWTUtility jWTUtility, JwtResponse jwtResponse) {
//		this.userService = userService;
//		this.jWTUtility = jWTUtility;
//		this.jwtResponse = jwtResponse;
//	}
//
//	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Object> postUser(@RequestBody User user) {
//		try {
//			userService.saveUser(user);
//			return new ResponseEntity<>(user, HttpStatus.CREATED);
//		} catch (Exception e) {
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
//		}
//	}
//
//	@PostMapping(value = "/token", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<JwtResponse> getToken(@RequestBody Login login) {
//		try {
//			logger.info(".........login.getPassword()............ {}", login.getPassword());
//			if (login.getUserName() == null || login.getPassword() == null) {
//				throw new UserNotFoundException("UserName or Password is Empty");
//			}
//			User user = userService.getUserByNameAndPassword(login.getUserName(), login.getPassword());
//			logger.info("{} .........user.etPassword()............ {}", user.getUserName(), user.getPassword());
//			jwtResponse.setMessage("");
//			jwtResponse.setJwtToken(jWTUtility.generateToken(user));
//
//			return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
//		} catch (UserNotFoundException e) {
//			jwtResponse.setMessage("UserName or Password is Invalid");
//			jwtResponse.setJwtToken("");
//			return new ResponseEntity<>(jwtResponse, HttpStatus.CONFLICT);
//		}
//	}
//
//}
