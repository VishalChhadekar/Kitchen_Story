package com.kitchen_story.service;

import com.kitchen_story.entity.AppUser;
import com.kitchen_story.model.AppUserModel;

public interface AppUserService {

	AppUser registerUser(AppUser appUser);

	AppUser updateCredentials(AppUserModel appUser);

}
