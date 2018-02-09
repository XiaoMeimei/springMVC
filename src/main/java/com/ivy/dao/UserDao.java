package com.ivy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ivy.entity.User;

public interface UserDao {

	public User getUserByUsername(@Param("username")String username);
	
	public void createUser(User user) throws Exception;
	
	public String getAuthorityList(@Param("username")String username);
	
}
