package com.ivy.service;

import com.ivy.entity.User;

public interface UserService {

	public boolean getUserByUsername(String username);
	
	public boolean createUser(User user);
}
