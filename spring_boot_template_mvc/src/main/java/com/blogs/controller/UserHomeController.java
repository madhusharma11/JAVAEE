package com.blogs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // spring bean containing req handling logic
public class UserHomeController {
	public UserHomeController() {
		System.out.println("in const of " + getClass());
	}

	// add method to render login page
	@GetMapping("/")
	public String showLoginForm()
	{
		System.out.println("in showLoginForm!!!!");
		return "/users/login";  //handler return LVN to DS---->VR--->
		//AVN:/WEB-INF/viewa/users/login
	}
}
