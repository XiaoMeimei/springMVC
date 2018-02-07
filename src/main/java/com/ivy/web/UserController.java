package com.ivy.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ivy.entity.User;
import com.ivy.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	protected static Logger logger = Logger.getLogger("controller");
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(@RequestParam(value = "error", required = false) boolean error,ModelMap model) {

		logger.debug("Received request to show login page");

		if (error == true) {
			// Assign an error message
			model.put("error","You have entered an invalid username or password!");
		} else {
			model.put("error", "");
		}
		return "loginpage";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public void getRegisterPage(@ModelAttribute User user, HttpServletResponse response) throws IOException {
		Object salt = null;
		response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        user.setIsvip(0);
        user.setEnabled(true);
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        String encodePassword = md5.encodePassword(user.getPassword(), salt);
        user.setPassword(encodePassword);
        boolean createState = userService.createUser(user);
        if (createState) {
        	response.getWriter().print("恭喜您，注册成功！");
        } else {
        	response.getWriter().print("注册失败，请重新注册！");
        }
        
	}

	@RequestMapping(value = "/validateUsername", method = RequestMethod.GET)
	public void validateUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
		Boolean isExist = userService.getUserByUsername(username);
		response.getWriter().print(isExist);
	}
	
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String getDeniedPage() {

		logger.debug("Received request to show denied page");

		return "deniedpage";
	}
}
