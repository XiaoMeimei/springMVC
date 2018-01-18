package com.ivy.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ivy.BaseTest;
import com.ivy.entity.User;

import junit.framework.Assert;

public class UserDaoTest extends BaseTest{
	
	@Autowired
	private UserDao userDao;
	
	public User user;
	
	@Before
	public void setUp(){
		user = new User();
		user.setUsername("Tonny");
		user.setPassword("12345");
//		user.setIsvip(0);
	}

	@Test
	public void testCreateUser() {
		boolean isCreateSuccessful = false;
		try{
			userDao.createUser(user);
			isCreateSuccessful = true;
		}catch(Exception e){
			isCreateSuccessful = false;
			e.getStackTrace();
		}
		Assert.assertTrue(isCreateSuccessful);
	}
	
	@Test
	public void testCheckUser() throws Exception {
		
		String userName = userDao.checkUser(user);
		Assert.assertEquals(user.getUsername(), userName);
	}
	
	
	@Test
	public void testFindUser() throws Exception {
		
		int userId = userDao.findUser(user.getUsername());
		Assert.assertNotNull(userId);
	}
}
