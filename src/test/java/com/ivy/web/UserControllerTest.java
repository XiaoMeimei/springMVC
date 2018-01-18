package com.ivy.web;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ivy.BaseTest;

@WebAppConfiguration
public class UserControllerTest  extends BaseTest{

	private MockMvc mockMvc;
	@Autowired
	private UserController userController;
	
	public String user = "username=Tom&password=12345&isvip=0";
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	public void testLoginPage() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/user/loginPage"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void testLogin() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/user/login?username=Tom&password=12345&isvip=0"))
		.andExpect(status().isOk());
	}
	
}
