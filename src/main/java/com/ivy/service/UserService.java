package com.ivy.service;

import com.ivy.entity.User;

public interface UserService {

	/**
	 * create a user
	 */
	void createUser(User user) throws Exception;
	
	/**
	 * find a user by userName
	 */
	boolean findUser(String userName) throws Exception;
	
	/**
	 * check user login message
	 */
	String checkUser(User user) throws Exception;
	
	/**
	 * whether is vip
	 */
	int isVip(String userName) throws Exception;
}
