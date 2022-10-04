package com.kitchen_story.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kitchen_story.entity.AppUser;
import com.kitchen_story.model.CustomerUserDetails;
import com.kitchen_story.repository.AppUserDetailsServiceRepository;



@Service
public class AppUserDetailService implements UserDetailsService{
	
	@Autowired
	private AppUserDetailsServiceRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//Logic to get user from DB
		AppUser user = repository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		//for Demo
//		return new User("admin", "$2a$12$ncg0HWn51jKYH7th8gfQNulPuBwnG2J8tMloUnJfxb8GJSKQ7fS6i", new ArrayList<>());
	
		/*
		we must return the object of UserDetails: since it is an interface, thus we have to create 
		a class CustomeUserDetails which implements UserDetails.
		We will instantiate CustomerUserDetails and pass the AppUser object to it
		*/
		return new CustomerUserDetails(user);
	}

	
}
