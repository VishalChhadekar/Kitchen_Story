package com.kitchen_story.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.kitchen_story.entity.AppUser;

@Repository
public interface AppUserDeatailsRepository extends JpaRepository<AppUser, Long> {

	UserDetails findByUsername(String username);

}
