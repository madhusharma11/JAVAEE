package com.blogs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blogger")
public class AdminController {
public AdminController()
{
	System.out.println("in constructor of"+getClass());
}

@GetMapping("/home")
public String bloggerHome()
{
	System.out.println("in blogger home");
	return "/blogger/home";
}
}
