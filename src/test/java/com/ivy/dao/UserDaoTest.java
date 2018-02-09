package com.ivy.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ivy.BaseTest;
import com.ivy.entity.User;

public class UserDaoTest extends BaseTest{

	@Autowired
	private UserDao userDao;
	
	private User user = new User();
	
	@Before
	public void setUp(){
		user.setUsername("ivy1");
		user.setPassword("ivy2");
		user.setEnabled(true);
		user.setIsvip(0);
	}

	@Test
	public void testCreateUser() throws Exception {
		userDao.createUser(user);
	}


}
