package com.ivy.dao;

import com.ivy.entity.User;

public interface UserDao {
	
	public void createUser(User user) throws Exception;
	public String checkUser(User user) throws Exception;
	public Integer findUser(String username) throws Exception;
	public Integer isVip(String user_name)throws Exception;
	
}

