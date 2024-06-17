package com.blogs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BloggerController {
public BloggerController()
{
	System.out.println("in constructor of"+getClass());
}

@GetMapping("/home")
public String bloggerHome()
{
	System.out.println("in admin home");
	return "/admin/home";
}
}
