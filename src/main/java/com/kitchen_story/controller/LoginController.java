package com.kitchen_story.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kitchen_story.model.JWTRequest;
import com.kitchen_story.model.JWTResponse;
import com.kitchen_story.service.AppUserDetailService;
import com.kitchen_story.utility.JWTUtility;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AppUserDetailService appUserDetailService;
	
	@Autowired
	private JWTUtility jwtUtility;
	
	@GetMapping("/hello")
	public String hello() {
		System.out.println("Accessing api");
		return "Welcom to app";
	}
	
	@PostMapping("/authenticate")
	public JWTResponse authenticate(@RequestBody JWTRequest jwtRequest) throws Exception {
		// using try catch: if authentication fails throw exception
		try {

			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}

		// if authentication successful
		// get user details from userDetaislService
		final UserDetails userDetails = appUserDetailService.loadUserByUsername(jwtRequest.getUsername());
		// generate token
		final String token = jwtUtility.generateToken(userDetails);
		return new JWTResponse(token);

	}

}
