package com.app.pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.dao.CandidateDaoImpl;
import com.app.dao.UserDaoImpl;
import com.app.entities.User;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		try(PrintWriter out=response.getWriter()) {
			HttpSession hs=request.getSession();
			User user=(User) hs.getAttribute("user_info");
			System.out.println("Logout  servlet , session is new " + hs.isNew());// f
			System.out.println("session ID " + hs.getId());// same for the same clnt
			System.out.println("HS "+hs);
			System.out.println("request "+request);
			if(user.getRole().equals("voter")) {
				out.print("<h5>You have already voted !!!!!!!!!</h5>");
			}
			hs.invalidate();
			out.print("<h5>You have logged out....</h5>");
			out.print("Hello,"+user.getFirstName()+" "+user.getLastName());
			out.print("Visit Again!!!");
			out.print("<h5><a href='login.html'>login</a></h5>");
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		try(PrintWriter pw=resp.getWriter()) {
			HttpSession hs=req.getSession();
			User user=(User) hs.getAttribute("user_info");
			pw.print("<h5>Hello , "+user.getFirstName()+" "+user.getLastName()+"</h5>");
			UserDaoImpl userDao=(UserDaoImpl)hs.getAttribute("user_dao");
			CandidateDaoImpl candidateDao=(CandidateDaoImpl)hs.getAttribute("candidate_dao");

			int candidateId=Integer.parseInt(req.getParameter("candidate_id"));

			pw.print("<h5>"+userDao.updateVotingStatus(user.getUserId())+"</h5>");

			pw.print("<h5>"+candidateDao.incrementCandidateVotes(candidateId)+"</h5>");

			hs.invalidate();
			pw.print("<h5>You have logged out....</h5>");
		} catch (Exception e) {
			throw new ServletException("err in do-post of "+getClass(), e);
		}
	}


}
