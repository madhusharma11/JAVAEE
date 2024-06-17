package com.app.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.dao.CandidateDao;
import com.app.entities.Candidate;


//@WebServlet("/ResultAdminServlet")
public class ResultAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ResultAdminServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		CandidateDao candi=(CandidateDao) session.getAttribute("candidate_dao");
		try (PrintWriter pw=response.getWriter()){
			List<Candidate> candidate=candi.getResult();
			response.setContentType("text/html");
			pw.print("<h4>Hello Admin!!!!<br>Top two Candidates::</h4>");
		
			for(Candidate c:candidate)
			{
				pw.print(c);
				pw.print("<br>");
				
			}
			
			pw.print("<h4><a href='logout'</a>logout</h4>");
			
		} catch (Exception e){
			System.out.println(e);
		}
	}
		

}
