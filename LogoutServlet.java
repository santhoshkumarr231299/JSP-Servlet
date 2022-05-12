package logoutservlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("frontpage.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null)
		{
			request.setAttribute("alert","Session Expired, Log in again");
			request.getRequestDispatcher("frontpage.jsp").forward(request,response);
			return ;
		}
		if(session.getAttribute("username") == null)
		{
			request.setAttribute("alert","Session Expired, Log in again");
			request.getRequestDispatcher("frontpage.jsp").forward(request,response);
			return ;
		} 
		
		response.setHeader("Cache-Control","no-cache"); 
         response.setHeader("Cache-Control","no-store"); 
         response.setDateHeader("Expires", 0); 
         response.setHeader("Pragma","no-cache");
	
			session.removeAttribute("username");
			if(session.getAttribute("admin")!=null)
				session.removeAttribute("admin");
			if(session.getAttribute("data")!=null)
				session.removeAttribute("data");
			session.invalidate();
		
		request.setAttribute("alert", "You have Logged Out Successfully");
		//response.sendRedirect("frontpage.jsp");
		request.getRequestDispatcher("frontpage.jsp").forward(request,response);
	}

}
