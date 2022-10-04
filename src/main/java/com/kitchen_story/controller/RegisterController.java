package com.kitchen_story.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kitchen_story.entity.AppUser;
import com.kitchen_story.service.AppUserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RegisterController {

	@Autowired
	private AppUserService appUserService;

	// REGISTRATION
	@PostMapping("/register")
	public ResponseEntity<AppUser> registerUser(@RequestBody AppUser appUser) {
		AppUser user = appUserService.registerUser(appUser);
		return new ResponseEntity<AppUser>(user, HttpStatus.OK);
	}
}
