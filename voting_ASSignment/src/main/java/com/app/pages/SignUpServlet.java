package com.app.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.dao.UserDaoImpl;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/voter_register")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao;

	public void init(ServletConfig config) throws ServletException {
		try {
			userDao = new UserDaoImpl();
		} catch (Exception e) {
			throw new ServletException("error in init " + getClass(), e);
		}
	}
	
	
	
//	
//	@Override
//	public void init() throws ServletException
//	{
//		
//		userDao=new UserDaoImpl();
//	}

	
	public void destroy() {
		try {
			userDao.cleanUp();
		} catch (SQLException e) {
			System.out.println("error in destroy!!!");
		}

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String firstname = request.getParameter("fn");
		String lasttname = request.getParameter("ln");
		String email = request.getParameter("em");
		String pass = request.getParameter("pass");
		String dob = request.getParameter("dob");
		String role = request.getParameter("role");
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
	
			LocalDate curdate = LocalDate.now();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate dateTime = LocalDate.parse(dob, formatter);
			System.out.println("email:04"+email);

			if ((curdate.getYear() - dateTime.getYear() > 20)&&((userDao.validateEmail(email))&&(email!=null))) {
				//if ((userDao.validateEmail(email))&&(email!=null)) {
					userDao.voterRegistration(firstname, lasttname, email, pass, dob, role);
					
					pw.print("User registered!!!!");
					pw.print("<h4><a href ='login.html' >clicck to Login</a></h4>");
					System.out.println("email:05"+email);
			//	}
			} else
			{
				System.out.println("Invalid Information!!!!");
				pw.print("<h3><a href= 'voter_registration.html'>Invalid!!!!<br> Please retry </a></h3>");
				
			}
		} catch (Exception e) {
			//e.printStackTrace();
			
		}
	}

}
