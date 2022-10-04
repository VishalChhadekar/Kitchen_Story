package com.kitchen_story.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kitchen_story.entity.AppUser;
import com.kitchen_story.model.AppUserModel;
import com.kitchen_story.repository.AppUserServiceRepository;

@Service
public class AppUserServiceImp implements AppUserService {

	@Autowired
	private AppUserServiceRepository appUserServiceRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public AppUser registerUser(AppUser appUser) {
		return appUserServiceRepository.save(appUser);
	}

	@Override
	public AppUser updateCredentials(AppUserModel appUser) {
		AppUser user = appUserServiceRepository.findByUsername(appUser.getUsername());
		if (user == null) {
			throw new UsernameNotFoundException("User is not present");
		}
		//updates password
		if (appUser.getPassword() != null) {
			user.setPassword(passwordEncoder.encode(appUser.getPassword()));;
		}
		//save user
		appUserServiceRepository.save(user);
		return user;
	}

}
