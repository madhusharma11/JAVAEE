package com.app.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.app.utils.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.dao.UserDaoImpl;
import com.app.entities.User;

@WebServlet(value = "/login", loadOnStartup = 1) 
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao;
	
	
	@Override
	public void init() throws ServletException {
		System.out.println("in init of " + getClass());
	ServletConfig config=	getServletConfig();
	String URL=config.getInitParameter("url");
	String userName=config.getInitParameter("user_name");
	String pass=config.getInitParameter("password");
	try {
		DBUtils.openConnection(URL,userName,pass);
	} catch (SQLException e1) {
		
		e1.printStackTrace();
	}
	
	}

	
	@Override
	public void destroy() {
		try {		
			userDao.cleanUp();
		} catch (Exception e) {
			System.out.println("err in destroy - " + getClass());
		}
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {			
			String email = request.getParameter("em");
			String password = request.getParameter("pass");	
			User user = userDao.signIn(email, password);			
			if (user == null) {				
				pw.print("<h5>Invalid Login , Please  <a href='login.html'>Retry</a><h5>");
			} else {				
				Cookie c1=new Cookie("user_details",user.toString());				
				response.addCookie(c1);
				if (user.getRole().equals("voter")) {
					if(user.isStatus())
						response.sendRedirect("logout");
					else 
						response.sendRedirect("candidate_list");	
				} else {
					response.sendRedirect("admin");
				}
			}
		} 
		catch (Exception e) {
			throw new ServletException("err in servicing " + getClass(), e);
		}
	}
}
