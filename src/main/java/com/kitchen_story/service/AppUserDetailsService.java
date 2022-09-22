package com.kitchen_story.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kitchen_story.repository.AppUserDeatailsRepository;



@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private AppUserDeatailsRepository appUserDeatailsRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = appUserDeatailsRepository.findByUsername(username);
		if(userDetails==null) {
			throw new UsernameNotFoundException("User does not present"); 
		}
		/*
		we must return the object of UserDetails: since it is an interface, thus we have to create 
		a class CustomeUserDetails which implements UserDetails.
		We will instantiate CustomerUserDetails and pass the AppUser object to it
		*/
		
		return null;
	}

}
