package com.ivy.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ivy.BaseTest;
import com.ivy.entity.User;
import com.ivy.entity.UserInfo;
import com.ivy.service.UserService;

import junit.framework.Assert;

public class UserServiceImplTest extends BaseTest{
	
	@Autowired
	private UserService userService;
	
	public User user;
	
	@Before
	public void setUp(){
		user = new User();
		user.setUsername("Tom");
		user.setPassword("12345");
		user.setIsvip(0);
	}

	@Test
	public void testCreateUser() {
		boolean isCreateSuccessful = false;
		try{
			userService.createUser(user);
			isCreateSuccessful = true;
		}catch(Exception e){
			isCreateSuccessful = false;
			e.getStackTrace();
		}
		Assert.assertTrue(isCreateSuccessful);
	}
	
/*	@Test
	public void testCheckUser() throws Exception {
		
		String userName = userService.checkUser(user);
		Assert.assertEquals(user.getUsername(), userName);
	}
	
	
	@Test
	public void testFindUser() throws Exception {
		
		boolean isExist = userService.findUser(user.getUsername());
		Assert.assertTrue(isExist);
	}*/
}
