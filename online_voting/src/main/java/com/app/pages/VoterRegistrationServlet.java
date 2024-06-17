package com.app.pages;

import static com.app.utils.DBUtils.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.dao.UserDaoImpl;
import com.app.entities.User;


//@WebServlet("/voter_register")
public class VoterRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao;

	/**
	 * @see Servlet#init()
	 */
	public void init() throws ServletException {
		try {
			ServletConfig config = getServletConfig();
		
			openConnection(config.getInitParameter("db_url"), config.getInitParameter("user_name"),
					config.getInitParameter("password"));

			userDao = new UserDaoImpl();
		} catch (Exception e) {
			throw new ServletException("err in init of " + getClass(), e);
		}
	}

	
	
	public void destroy() {
		try {
			userDao.cleanUp();
			closeConnection();
		} catch (Exception e) {
			System.out.println("err in destroy of " + getClass());
		}
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
		
			HttpSession session = request.getSession();
	
			String fName = request.getParameter("fn");
			String lName = request.getParameter("ln");
			String email = request.getParameter("em");
			String password = request.getParameter("pass");
			LocalDate birthDate = LocalDate.parse(request.getParameter("dob"));
			int age=Period.between(birthDate, LocalDate.now()).getYears();
			if(age >= 21) {
				
				User newVoter=new User(fName, lName, email, 
						password,Date.valueOf(birthDate));
				
				String regStatus = userDao.voterRegistration(newVoter);
				pw.print("<h5> "+regStatus+"</h5>");
				
				session.invalidate();
				pw.print("<h5> Proceed to <a href='login.html'>Login</a></h5>");
				
			} else {
				pw.print("<h5>Voter registration failed , Invalid Age !!!!!</h5>");
				pw.print("<h5> <a href='voter_registration.html'>Retry Registration</a></h5>");
				
			}

		} catch (Exception e) {
			throw new ServletException("err in do-post of "+getClass(), e);
		}
	}

}
