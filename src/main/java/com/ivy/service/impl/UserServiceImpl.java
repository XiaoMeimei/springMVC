package com.ivy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivy.dao.UserDao;
import com.ivy.entity.User;
import com.ivy.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	

	public boolean getUserByUsername(String username) {
		
		User user = userDao.getUserByUsername(username);
		if (user != null) {
			return true;
		} else {
			return false;
		}
	}


	public boolean createUser(User user) {
		try {
			userDao.createUser(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


}
