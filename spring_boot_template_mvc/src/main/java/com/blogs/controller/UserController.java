package com.blogs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blogs.entities.Role;
import com.blogs.entities.User;
import com.blogs.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	@PostMapping("/login")
	
	public String processloginForm(@RequestParam String email, @RequestParam String password,ModelMap map,HttpSession session)
	{//@RequestParam(name="email") String em===this is also possible
		try {
		System.out.println("in process login Form!!");
		System.out.println(email+" "+password);
		//invoke service layer method
		User user=userService.signInUser(email, password);
		System.out.println("user:"+user);
		//check the role in case of admin ...forward client to admin home page
		//add user details and message under model attribute
		session.setAttribute("message","login successful");
		session.setAttribute("user_details", user);
		if(user.getRole()==Role.ADMIN)
			return "redirect:/admin/home/";
		return "redirect:/blogger/home/";
		
		//check the role in case of blogger ...forward client to blogger home page
//		if(user.getRole()==Role.BLOGGER)
//		{
//			return "/blogger/home/";
//		}
	}catch (RuntimeException e)
		{
		e.printStackTrace();
		System.out.println("in error"+e);
		//invalid login...forward the client to the login form
		map.addAttribute("message", e.getMessage());
		return "/users/login";  //AVN: /WEB-INF/views/users/login
		}
		
		
	}

}
