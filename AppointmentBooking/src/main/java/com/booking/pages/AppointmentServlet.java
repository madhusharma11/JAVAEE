package com.booking.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.booking.DButils.DBUtils;
import com.booking.core.Doctor;
import com.booking.dao.AppointmentDao;
import com.booking.dao.AppointmentDaoImpl;
import com.booking.dao.DoctorDao;
import com.booking.dao.DoctorDaoImpl;

//@WebServlet("/book_appointment")
public class AppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DoctorDao doctorDao;
	private AppointmentDao appointmenetDao;

	public AppointmentServlet() {
		super();

	}

	public void init() throws ServletException {

		ServletConfig config1 = getServletConfig();
		String url = config1.getInitParameter("db_url");
		String user = config1.getInitParameter("user");
		String pass = config1.getInitParameter("password");

		try {
			DBUtils.openConnection(url, user, pass);
			appointmenetDao = new AppointmentDaoImpl();
			doctorDao = new DoctorDaoImpl();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {

		try {
			DBUtils.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("pnme");
		String blood = request.getParameter("blood");
		String pwd = request.getParameter("pass");
		String dateTime = request.getParameter("date and time");

		System.out.println(name + blood + pwd + dateTime);
		response.setContentType("text/html");


		try (PrintWriter pw = response.getWriter()) {
			List<Doctor> list = doctorDao.getDoctors();
			pw.print("<form action='bookAppointment'>");
			pw.print("<table>");
			for(Doctor d:list)
			{
			pw.print("<tr><td><input type='radio' name='id' id='123'/></td>");
					pw.print("<td>"+d.getName()+"<br>"
			                 +d.getSpeciality()+"<br>"+d.getMorningSlot()
			                 +"-"+d.getEveningSlot()+"</td></tr>");
			
			}
			pw.print("<input type='submit' value='submit'/>");
			pw.print("</form>");
			
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
