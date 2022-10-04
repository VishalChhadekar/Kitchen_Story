package com.kitchen_story.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kitchen_story.entity.AppUser;

@Repository
public interface AppUserDetailsServiceRepository extends JpaRepository<AppUser, Long>{

	AppUser findByUsername(String username);

}
