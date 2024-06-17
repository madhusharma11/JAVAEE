package com.app.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.dao.CandidateDaoImpl;
import com.app.dao.UserDaoImpl;
import com.app.entities.User;
import static com.app.utils.DBUtils.*;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet(value = "/login", loadOnStartup = 1) 
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao;
	private CandidateDaoImpl candidateDao;

	public LoginServlet() {
		System.out.println("in def ctor ");
		System.out.println("config " + getServletConfig());// null
	}

	@Override
	public void init() throws ServletException {
		ServletConfig config = getServletConfig();
		System.out.println("in init of " + getClass()+" cofig "+config);//not null
		try {
			//open db connection once
			openConnection(config.getInitParameter("db_url"),
					config.getInitParameter("user_name"),
					config.getInitParameter("password"));
			userDao = new UserDaoImpl();
			candidateDao=new CandidateDaoImpl();
		} catch (Exception e) {
			throw new ServletException("err in init - " + getClass(), e);
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			userDao.cleanUp();
			candidateDao.cleanUp();
			closeConnection();
		} catch (Exception e) {
			System.out.println("err in destroy - " + getClass());
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			
			String email = request.getParameter("em");
			String password = request.getParameter("pass");
			
			System.out.println(email+password);
			User user = userDao.signIn(email, password);
			System.out.println(user);
		
			if (user == null) {
			
				pw.print("<h5>Invalid Login , Please  <a href='login.html'>Retry</a><h5>");
			} else {
				HttpSession session = request.getSession();
				System.out.println("Login servlet , session is new " + session.isNew());// t
				System.out.println("session ID " + session.getId());
				session.setAttribute("user_info", user);
				session.setAttribute("user_dao", userDao);
				session.setAttribute("candidate_dao", candidateDao);
				if (user.getRole().equals("voter")) {
					
					if (user.isStatus()) 
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
