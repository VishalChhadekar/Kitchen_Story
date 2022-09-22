package com.kitchen_story.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kitchen_story.model.JWTRequest;
import com.kitchen_story.model.JWTResponse;
import com.kitchen_story.utility.JWTUtility;

@RestController
public class AdminController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWTUtility jwtUtility;
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	@GetMapping("/")
	public String home() {
		return "Home page";
	}
	@PostMapping("/authenticate")
	public ResponseEntity<JWTResponse>  authenticate(@RequestBody JWTRequest jwtRequest) throws Exception {
		// using try catch: if authentication fails throw exception
		System.out.println(jwtRequest.toString());
		try {
			System.out.println("In try block");
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		// if authentication is successful
		// get user details from user service.
		final UserDetails userDetails = 
				userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		System.out.println("After authentication:" + userDetails);
		// generate token
		final String token = jwtUtility.generateToken(userDetails);
//		return new JWTResponse(token);
		return new ResponseEntity<JWTResponse> (new JWTResponse(token), HttpStatus.BAD_REQUEST );

	}
}
