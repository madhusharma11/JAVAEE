package com.app.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.dao.CandidateDaoImpl;
import com.app.entities.Candidate;
import com.app.entities.User;

/**
 * Servlet implementation class CandidateListServlet
 */
@WebServlet("/candidate_list")
public class CandidateListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		
		try (PrintWriter pw = response.getWriter()) {
			pw.print("<h5>in candidate list page....: successful login!</h5>");
			
			HttpSession hs = request.getSession();
			System.out.println("Candidate list servlet , session is new " + hs.isNew());// f
			System.out.println("session ID " + hs.getId());
			User user = (User) hs.getAttribute("user_info");
			if (user != null) {
				pw.print("<h4> Hello " + user.getFirstName() + " " + user.getLastName() + "</h4>");
				pw.print("<h3 align='center'> Candidate List </h3>");
		
				CandidateDaoImpl dao = (CandidateDaoImpl) hs.getAttribute("candidate_dao");
				
				List<Candidate> candidateList = dao.getAllCandidates();
				
				pw.print("<form action='logout' method='post'>");
				for (Candidate c : candidateList) {
					pw.print("<h5><input type='radio' name='candidate_id' "
							+ "value='" + c.getCandidateId() + "'/>"
							+ c.getCandidateName() + " " + c.getParty() + "</h5>");
				}
				pw.print("<h5><input type='submit' value='Vote'/></h5>");
				
				pw.print("</form>");
			} else
				pw.print("<h4> No Cookies , Session Tracking Failed !!!!</h4>");
			} catch (Exception e) {
			throw new ServletException("err in do-get of " + getClass(), e);
		}
	}

}
