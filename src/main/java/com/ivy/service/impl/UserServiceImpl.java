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
	
	public void createUser(User user) throws Exception {
		Boolean found = findUser(user.getUsername());
		if(!found)
		   userDao.createUser(user);
		else
			throw new RuntimeException();
	}
	
	public String checkUser(User user ) throws Exception{
		return userDao.checkUser(user);
	}
	
	public boolean findUser(String username) throws Exception{
		Integer found = userDao.findUser(username);
		if(found==null || found<1)  return false;
		return true;
	}

	public int isVip(String user_name)throws Exception {
		return userDao.isVip(user_name);
	}
	
}