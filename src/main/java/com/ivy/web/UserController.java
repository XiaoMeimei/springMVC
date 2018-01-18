package com.ivy.web;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ivy.entity.User;
import com.ivy.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute User user, Model model, HttpServletRequest request) {

		if ("".equals(user.getUsername()) || "".equals(user.getPassword())) {
			model.addAttribute("error", "UserName or password error.");
			return "login";
		}
		try {
			String userName = userService.checkUser(user);
			if (userName != null && (!"".equals(userName))) {
				request.getSession().setAttribute("user", user);
				model.addAttribute("welcome", "welcome to my first page");
				model.addAttribute("currentUser", userName);
				return "home";
			}
			model.addAttribute("error", "UserName or password error.");
			return "login";
		} catch (Exception e) {

			return "error";
		}

	}
	
	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public String loginPage(Model model) {
		return "login";
	}
}
